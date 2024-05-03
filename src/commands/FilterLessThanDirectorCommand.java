package commands;

import classes.Movie;
import classes.Person;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Класс команды, отвечающей за вывод элементов, значение режиссера которых меньше данного.
 */
public class FilterLessThanDirectorCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду вывода элементов, значение режиссера которых меньше данного.
     * @param collectionManager утилита для работы с коллекцией
     */
    public FilterLessThanDirectorCommand(CollectionManager collectionManager){ // поменять описание
        super("filter_less_than_director {director}", "Вывести элементы, значение поле director меньше заданного");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     * Получает коллекцию, затем получает список режиссеров всех фильмов.
     * Проходит по списку режиссеров.
     * Если режиссер меньше заданного, выводит фильм.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument){
        try {
            if (argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            LinkedList<Movie> movies = collectionManager.getMovieCollection();
            if (movies.isEmpty()) throw new CollectionIsEmptyException();
            LinkedList<String> moviesDirectors = movies.stream()
                    .map(Movie::getDirector)
                    .map(Person::getName)
                    .map(String::toLowerCase)
                    .collect(Collectors.toCollection(LinkedList::new));

            String directorToCompare = argument.trim().toLowerCase();
            for (String director : moviesDirectors){
                if (director.compareTo(directorToCompare) < 0)
                    System.out.println(movies.get(moviesDirectors.indexOf(director)));
            }

            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 1 аргумент - режиссер фильма");
        }
        catch (CollectionIsEmptyException e){
            System.err.println("Коллекция пуста!");
            return true;
        }
        return false;
    }
}
