package commands;

import classes.Movie;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Класс команды, отвечающей за вывод элементов с уникальным количеством оскаров.
 */
public class PrintUniqueOscarsCountCommand extends AbstractCommand{
    CollectionManager collectionManager;
    /**
     * Создает команду вывода элементов с уникальным количеством оскаров.
     * @param collectionManager утилита для работы с коллекцией
     */
    public PrintUniqueOscarsCountCommand(CollectionManager collectionManager) {
        super("print_unique_oscars_count"
                ,"вывести уникальные значения количества оскаров всех элементов в коллекции");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     * Получает коллекцию, создает список уникальных фильмов и появившихся количеств оскаров.
     * Проходит по фильмам коллекции.
     * Если количество оскаров фильма уникально, добавляет его в список появившихся количеств оскаров, а сам фильм - в список уникальных.
     * Иначе, проходит по уникальным фильмам и удаляет элемент, количество оскаров которого совпадает с текущим фильмом.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            if (collectionManager.getCollectionSize() == 0) throw new CollectionIsEmptyException();
            System.out.println("Вывод фильмов с уникальным количеством оскаров: ");

            LinkedList<Movie> movies = collectionManager.getMovieCollection();
            LinkedList<Movie> uniqueMovies = new LinkedList<>();
            LinkedList<Integer> appearedOscarsCount = new LinkedList<>();

            for (Movie movie : movies){
                int oscarCount = movie.getOscarsCount();
                if (!appearedOscarsCount.contains(oscarCount)){
                    uniqueMovies.add(movie);
                    appearedOscarsCount.add(oscarCount);
                }
                else {
                    uniqueMovies.removeIf(movieToRemove -> movieToRemove.getOscarsCount() == oscarCount);
                }
            }

//            uniqueMovies.forEach(movie -> System.out.println(movie.getName() + ": " + movie.getOscarsCount())); // имя: количество оскаров
            uniqueMovies.forEach(System.out::println); // просто элементы целиком

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
