package commands;

import classes.Movie;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;

import java.util.LinkedList;
/**
 * Класс команды, отвечающей за вывод элементов в порядке возрастания.
 */
public class PrintAscendingCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду вывода элементов в порядке возрастания.
     * @param collectionManager утилита для работы с коллекцией
     */
    public PrintAscendingCommand(CollectionManager collectionManager){
        super("print_ascending", "Вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     * Получает список фильмов и сортирует его.
     * Выводит каждый элемент нового списка.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            if (collectionManager.getCollectionSize() == 0) throw new CollectionIsEmptyException();
            System.out.println("Вывод элементов в порядке возрастания");
            LinkedList<Movie> movies = collectionManager.getMovieCollection();
            movies.sort(Movie::compareTo);
            movies.forEach(System.out::println);
            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов");
            return false;
        }
        catch (CollectionIsEmptyException e){
            System.err.println("Коллекция пуста!");
            return true;
        }
    }
}
