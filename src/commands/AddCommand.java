package commands;

import classes.*;
import exceptions.InvalidScriptInputException;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;
import managers.InputManager;
import managers.MovieCreator;

import java.nio.channels.WritableByteChannel;
import java.util.Scanner;
/**
 * Класс команды, отвечающей за добавления фильма в коллекцию.
 */
public class AddCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final MovieCreator movieCreator;
    /**
     * Создает команду добавления элемента.
     * @param collectionManager утилита для работы с коллекцией
     * @param movieCreator утилита для создания фильма
     */
    public AddCommand(CollectionManager collectionManager, MovieCreator movieCreator) {
        super("add", "Добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.movieCreator = movieCreator;
    }
    /**
     * Выполняет команду добавления фильма в коллекцию.
     * Создает пустой фильм, с помощью movieCreator создает поля фильма, с помощью collectionManager добавляет фильм в коллекцию.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            {
                Movie movie = new Movie();
                movieCreator.createMovie(movie);
                movie.setId(collectionManager.generateId());

                collectionManager.addToCollection(movie);

                System.out.println("Элемент добавлен в коллекцию!");
                return true;
            }
        } catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов");
        } catch (InvalidScriptInputException ignored) {}
        return false;
    }
}
