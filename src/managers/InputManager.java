package managers;

import exceptions.RecursiveScriptException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Класс, отвечающий за ввод данных пользователем или из скрипта.
 */
public class InputManager {
    private final CommandManager commandManager;
    private final Scanner scanner;
    private final MovieCreator movieCreator;
    private final LinkedList<String> scriptStack = new LinkedList<String>();
    /**
     * Создания элемента класса InputManager для работы с вводом.
     * @param commandManager утилита для работы с командами
     * @param scanner откуда будут идти данные
     * @param movieCreator утилита для создания элементов
     */
    public InputManager(CommandManager commandManager, Scanner scanner, MovieCreator movieCreator){
        this.commandManager = commandManager;
        this.movieCreator = movieCreator;
        this.scanner = scanner;
    }
    /**
     * Обрабатывает команды, вводимые пользователем в консоль, пока не будет получена команда "exit".
     */
    public void interactiveMode(){
        String[] command;
        int commandStatus;
        try{
            do {
                command = (scanner.nextLine().trim() + " ").split(" ", 2);
                command[0] = command[0].toLowerCase();
                command[1] = command[1].trim();
                commandManager.addToHistory(command[0]);
                commandStatus = commandLaunch(command);
            } while (commandStatus != 2);
        } catch (NoSuchElementException e){
            System.out.println("Завершение работы");
        } catch (IllegalStateException e){
            System.err.println("Ошибка работы!");
        }
    }
    /**
     * Обрабатывает ввод команд из файла со скриптом, пока команды не закончатся или пока не будет получена ошибка.
     * @param argument путь к файлу со скриптом
     * @return статус, с которым была завершена работа скрипта
     */
    public int scriptMode(String argument){
        String [] command;
        int commandStatus = 0;
        scriptStack.add(argument);

        try (Scanner scriptScanner = new Scanner(new File(argument))){
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner userScanner = movieCreator.getScanner();
            movieCreator.setScanner(scriptScanner);
            movieCreator.setScriptMode();
            do {
                command = (scriptScanner.nextLine().trim() + " ").split(" ", 2); // [command, argument]
                command[1] = command[1].trim();

                while (scriptScanner.hasNextLine() && command[0].isEmpty()) { // скип пустых строк
                    command = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    command[1] = command[1].trim();
                }
                System.out.println(String.join(" ", command));
                commandManager.addToHistory(command[0]); // каждая команда скрипта
                if (command[0].equals("execute_script")){ // обработка рекурсии
                    for (String script : scriptStack){
                        if (command[1].equals(script)) throw new RecursiveScriptException();
                    }
                }
                commandStatus = commandLaunch(command);

            } while (scriptScanner.hasNextLine() && commandStatus == 0);
            movieCreator.setScanner(userScanner);
            if (scriptStack.size() == 1) movieCreator.setInteractiveMode();
            if (commandStatus == 1 && !(command[0].equals("execute_script") && !command[1].isEmpty()))
                System.out.println("Проверьте скрипт на корректность введенных данных!");

            return commandStatus;

        } catch (FileNotFoundException e) {
            System.err.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException e){
            System.err.println("Файл со скриптом пуст!");
        } catch (RecursiveScriptException e) {
            System.err.println("При вызове данного скрипта была получена рекурсия!");
        } finally{
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }
    /**
     * Запускает команды.
     * @return int commandStatus показывающий, была ли выполнена команда корректно
     */
    private int commandLaunch(String[] command) {
        switch (command[0]) {
            case "" -> {}
            case "add" -> {if (!commandManager.add(command[1])) return 1;}
            case "add_if_max" -> {if (!commandManager.addIfMax(command[1])) return 1;}
            case "clear" -> {if (!commandManager.clear(command[1])) return 1;}
            case "execute_script" -> {
                if (!commandManager.executeScriptCommand(command[1])) return 1;
                return scriptMode(command[1]);
            }
            case "exit" -> {
                if (!commandManager.exit(command[1])) return 1;
                return 2;
            }
            case "filter_less_than_director" -> {if (!commandManager.filterLessThanDirector(command[1])) return 1;}
            case "help" -> {if (!commandManager.help(command[1])) return 1;}
            case "history" -> {if (!commandManager.history(command[1])) return 1;}
            case "info" -> {if (!commandManager.info(command[1])) return 1;}
            case "print_ascending" -> {if (!commandManager.printAscending(command[1])) return 1;}
            case "print_unique_oscars_count" -> {if (!commandManager.printUniqueOscarsCount(command[1])) return 1;}
            case "remove_by_id" -> {if (!commandManager.removeById(command[1])) return 1;}
            case "remove_greater" -> {if (!commandManager.removeGreater(command[1])) return 1;}
            case "save" -> {if (!commandManager.save(command[1])) return 1;}
            case "show" -> {if (!commandManager.show(command[1])) return 1;}
            case "update" -> {if (!commandManager.update(command[1])) return 1;}
            default -> {if (!commandManager.noSuchCommand(command[0])) return 1;}
        }
        return 0;
    }
}
