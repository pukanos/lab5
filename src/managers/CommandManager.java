package managers;

import commands.*;
import exceptions.WrongNumberOfArgumentsException;

import java.util.Arrays;
import java.util.LinkedList;
/**
 * Класс, отвечающий за работу и хранение команд.
 */
public class CommandManager {
    int COMMAND_HISTORY_SIZE = 12;
    private final String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private final LinkedList<ICommand> commands;
    private final ICommand helpCommand;
    private final ICommand infoCommand;
    private final ICommand showCommand; //
    private final ICommand addCommand;
    private final ICommand updateCommand;
    private final ICommand removeByIdCommand;
    private final ICommand clearCommand;
    private final ICommand saveCommand;
    private final ICommand exitCommand;
    private final ICommand executeScriptCommand;
    private final ICommand addIfMaxCommand;
    private final ICommand removeGreaterCommand;
    private final ICommand historyCommand;
    private final ICommand filterLessThanDirectorCommand; //
    private final ICommand printAscendingCommand; //
    private final ICommand printUniqueOscarsCountCommand; //
    /**
     * Возвращает список команд.
     * @return список команд
     */

    public LinkedList<ICommand> getCommands() {
        return commands;
    }
    /**
     * Возвращает массив последних использованных команд.
     * @return массив команд
     */
    public String[] getCommandHistory() {
        return commandHistory;
    }
    /**
     * Создает элемент класса CommandManager для работы с командами.
     * @param helpCommand команда вывода помощи
     * @param infoCommand команда вывода информации о коллекции
     * @param updateCommand команда обновления элемента
     * @param addCommand команда добавления элемента
     * @param exitCommand команда завершения работы
     * @param historyCommand команда вывода истории использованных команд
     * @param removeByIdCommand команда удаления элемента по ID
     * @param saveCommand команда сохранения коллекции
     * @param showCommand команда вывода всех элементов коллекции
     * @param clearCommand команда очищения коллекции
     * @param printAscendingCommand команда вывода элементов коллекции в порядке возрастания
     * @param printUniqueOscarsCountCommand команда вывода элементов, количество оскаров которых уникально
     * @param filterLessThanDirectorCommand команда вывода элементов, значение режиссера которых меньше чем, у заданного фильма
     * @param removeGreaterCommand команда удаления элементов больше заданного
     * @param addIfMaxCommand команда добавления элемента, если он больше любого другого элемента коллекции
     * @param executeScriptCommand команда выполнения скрипта из заданного файла
     */
    public CommandManager(HelpCommand helpCommand, InfoCommand infoCommand, UpdateCommand updateCommand,
                          AddCommand addCommand, ExitCommand exitCommand, HistoryCommand historyCommand,
                          RemoveByIdCommand removeByIdCommand, SaveCommand saveCommand,ShowCommand showCommand,
                          ClearCommand clearCommand, PrintAscendingCommand printAscendingCommand, PrintUniqueOscarsCountCommand printUniqueOscarsCountCommand,
                          FilterLessThanDirectorCommand filterLessThanDirectorCommand, RemoveGreaterCommand removeGreaterCommand,
                          AddIfMaxCommand addIfMaxCommand, ExecuteScriptCommand executeScriptCommand){ //добавить команды
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.updateCommand = updateCommand;
        this.addCommand = addCommand;
        this.exitCommand = exitCommand;
        this.historyCommand = historyCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.clearCommand = clearCommand;
        this.filterLessThanDirectorCommand = filterLessThanDirectorCommand;
        this.printAscendingCommand = printAscendingCommand;
        this.printUniqueOscarsCountCommand = printUniqueOscarsCountCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.executeScriptCommand = executeScriptCommand;
        commands = new LinkedList<>(Arrays.asList(helpCommand, infoCommand, updateCommand, addCommand,
                exitCommand, historyCommand, removeByIdCommand, saveCommand, showCommand, clearCommand,
                filterLessThanDirectorCommand, printAscendingCommand, printUniqueOscarsCountCommand,
                removeGreaterCommand, addIfMaxCommand, executeScriptCommand));
    }
    /**
     * Добавляет заданную команду в историю использованных команд.
     * @param commandToStore команда, которую нужно добавить в историю
     */
    public void addToHistory(String commandToStore) {
        for (ICommand command : commands) {
            if (command.getCommandName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }
    /**
     * Выполняется в том случае, когда введенная команда не существует.
     * @param command ненайденная команда
     * @return false
     */
    public boolean noSuchCommand(String command) {
        System.out.println("Команда " + command + " была не найдена. Наберите 'help' для справки.");
        return false;
    }
    /**
     * Выводит справку по имеющимся командам.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean help(String argument){
        if (helpCommand.execute(argument)){
            for (ICommand command : commands){
                System.out.println(command.toString());
            }
            return true;
        }return false;
    }
    /**
     * Выводит информацию о коллекции.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean info(String argument){
        return infoCommand.execute(argument);
    }
    /**
     * Выводит элементы коллекции.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean show(String argument){
        return showCommand.execute(argument);
    }
    /**
     * Добавляет элемент в коллекцию.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean add(String argument){
        return addCommand.execute(argument);
    }
    /**
     * Добавляет элемент в коллекцию, если он больше любого другого элемента коллекции.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean addIfMax(String argument){
        return addIfMaxCommand.execute(argument);
    }
    /**
     * Обновляет элемент коллекции.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean update(String argument){
        return updateCommand.execute(argument);
    }
    /**
     * Удаляет элемент с заданным ID.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean removeById(String argument){
        return removeByIdCommand.execute(argument);
    }
    /**
     * Очищает коллекцию.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean clear(String argument){
        return clearCommand.execute(argument);
    }
    /**
     * Сохраняет коллекцию в файл.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean save(String argument){
        return saveCommand.execute(argument);
    }
    /**
     * Завершает работу программы.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean exit(String argument){
        return exitCommand.execute(argument);
    }
    /**
     * Выполняет скрипт из данного файла.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean executeScriptCommand(String argument){
        return executeScriptCommand.execute(argument);
    }
    /**
     * Удаляет элементы больше данного.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean removeGreater(String argument){
        return removeGreaterCommand.execute(argument);
    }
    /**
     * Выводит массив использованных команд.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean history(String argument){
        if (historyCommand.execute(argument)){
            if (commandHistory.length == 0) {
                System.out.println("Ни одной команды еще не было введено");
                return false;
            }
            else{
                System.out.println("История использования команд:");
                for (int i = commandHistory.length - 1; i != 0; i--){ // first -> last
                    if (commandHistory[i] != null) System.out.println(commandHistory[i]);
                }
//                for (String i:commandHistory){ // last -> first
//                    if (!(i == null)) System.out.println(i);
//                }
                return true;
            }
        }
        return false;
    }
    /**
     * Выводит элементы коллекции в порядке возрастания.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean printAscending(String argument){
        return printAscendingCommand.execute(argument);
    }
    /**
     * Выводит элементы, количество оскаров которых уникально.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean printUniqueOscarsCount(String argument){
        return printUniqueOscarsCountCommand.execute(argument);
    }
    /**
     * Выводит элементы, значение режиссера которых меньше заданного.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean filterLessThanDirector(String argument){
        return filterLessThanDirectorCommand.execute(argument);
    }
}
