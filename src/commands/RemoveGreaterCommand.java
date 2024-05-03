package commands;

import classes.Movie;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Класс команды, отвечающей за удаление элементов больше данного.
 */
public class RemoveGreaterCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду удаления элементов больше данного.
     * @param collectionManager утилита для работы с коллекцией.
     */
    public RemoveGreaterCommand(CollectionManager collectionManager){
        super("remove_greater {element}", "Удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     * Удаляет все элементы коллекции больше данного.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument){
        try{
        if (argument.isEmpty()) throw new WrongNumberOfArgumentsException();
        Movie movieToCompare = collectionManager.getByName(argument);
        if (movieToCompare == null) throw new NoSuchElementException();
        collectionManager.removeGreater(movieToCompare);
        System.out.println("Элементы удалены!");
        return true;
        } catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 1 значение - элемент коллекции");
        } catch (NoSuchElementException e){
            System.err.println("Фильма с таким названием нет в коллекции!");
        }
        return false;
    }
}
