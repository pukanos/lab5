package commands;

import classes.Movie;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;

import java.util.LinkedList;
/**
 * Класс команды, отвечающей за вывод коллекции.
 */
public class ShowCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду вывода коллекции.
     * @param collectionManager утилита для работы с коллекцией
     */
    public ShowCommand(CollectionManager collectionManager){
        super("show", "Вывести все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     * Получает коллекцию фильмов.
     * Если она не пуста, проходит по каждому ее элементу и выводит его.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            LinkedList<Movie> movies = collectionManager.getMovieCollection();
            if (movies.isEmpty()) throw new CollectionIsEmptyException();
            System.out.println("Элементы коллекции:");

            movies.forEach(System.out::println);

            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов");
            return false;
        }
        catch (CollectionIsEmptyException e){
            System.out.println("Коллекция пуста!");
            return true;
        }
    }
}
