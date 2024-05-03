package commands;

import classes.*;
import exceptions.CollectionIsEmptyException;
import exceptions.InvalidScriptInputException;
import exceptions.WrongNumberOfArgumentsException;
import managers.MovieCreator;
import managers.CollectionManager;

import java.util.Scanner;
/**
 * Класс команды, отвечающей за обновление элемента коллекции
 */
public class UpdateCommand extends AbstractCommand{
    MovieCreator movieCreator;
    CollectionManager collectionManager;
    Scanner scanner;
    /**
     * Создает команду обновления элемента
     * @param movieCreator утилита для создания элемента
     * @param collectionManager утилита для работы с коллекцией
     */
    public UpdateCommand(MovieCreator movieCreator, CollectionManager collectionManager) {
        super("update {id}", "Обновить значение элемента, ID которого равен заданному");
        this.collectionManager = collectionManager;
        this.movieCreator = movieCreator;
    }
    /**
     * Выполняет команду обновления элемента.
     * Получает элемент по его ID и меняет его полностью или только его выбранные поля.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument) {
        try{
            if (argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            if (collectionManager.getCollectionSize() == 0) throw new CollectionIsEmptyException();
            int id = Integer.parseInt(argument);
            scanner = movieCreator.getScanner();
            Movie movie = collectionManager.getById(id);

            boolean generalFlag = true;
            while (generalFlag){
                try {
                    System.out.println("Введите 1, если Вы хотите изменить элемент целиком," +
                            " 2, если только выбранные значения и 0, чтобы ничего не изменять");
                    String input = scanner.nextLine().trim();
                    int option = Integer.parseInt(input);
                    if (option == 1){
                        movieCreator.createMovie(movie);
                        break;
                    } else if (option == 2) {
                        boolean flag = true;
                        Scanner s = new Scanner(System.in);
                        while (flag){
                            System.out.println("Что вы хотите изменить: name, viewing status, viewer rating, oscars count," +
                                    "genre, mpaarating, director? (Чтобы выйти, введите 'exit')");
                            String line = s.nextLine().trim().toLowerCase();
                            String[] ll = (line + " ").split(" ", 2);
                            switch (line){
                                case "name" -> movie.setName(movieCreator.createName());
                                case "viewing status" -> {
                                    movie.getCoordinates().setX(movieCreator.createX());
                                    if (movie.getCoordinates().getX() == 1){
                                        movie.getCoordinates().setY(movieCreator.createY());
                                    }
                                    else{
                                        movie.getCoordinates().setY(0);
                                    }
                                }
                                case "viewer rating"-> {
                                    if (movie.getCoordinates().getX() == 1) movie.getCoordinates().setY(movieCreator.createY());
                                    else System.out.println("Посмотрите фильм, прежде чем ставить оценку");
                                }
                                case "oscars count"-> movie.setOscarsCount(movieCreator.createOscarsCount());
                                case "genre"-> movie.setGenre(movieCreator.createMovieGenre());
                                case "mpaarating"-> movie.setMpaaRating(movieCreator.createMpaaRating());
                                case "director"-> movie.setDirector(movieCreator.createPerson());
                                case "exit" -> generalFlag = false;
                                default -> {
                                    System.err.println("Поле введено неверно!");
                                    continue;
                                }
                            }
                            if (ll[0].equals("exit")) break;
                            while (true){
                                System.out.println("Хотите ли вы изменить что-нибудь еще? (+/-)");
                                String b = s.nextLine().trim().toLowerCase();
                                if (b.equals("+")){
                                    break;
                                } else if (b.equals("-")) {
                                    generalFlag = false;
                                    flag = false;
                                    break;
                                }
                                else{
                                    System.out.println("Введите +/-");
                                }
                            }
                        }
                    } else if (option == 0) {
                        break;
                    } else {
                        System.err.println("Введено неверное значение!");
                    }

                }
                catch (NumberFormatException e){
                    System.err.println("Введено не число!");
                } catch (InvalidScriptInputException ignored) {}
            }
            collectionManager.updateTime();
            System.out.println("Завершение обновления элемента");
            return true;

        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 1 аргумент: ID");
            return false;
        } catch (CollectionIsEmptyException e) {
            throw new RuntimeException(e);
        }
    }
}
