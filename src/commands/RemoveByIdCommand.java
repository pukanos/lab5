package commands;

import classes.Movie;
import exceptions.CollectionIsEmptyException;
import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;
import java.util.NoSuchElementException;
/**
 * Класс команды, отвечающей за удаление элемента по его ID.
 */
public class RemoveByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду удаления элемента по его ID.
     * @param collectionManager утилита для работы с коллекцией
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id {id}", "Удалить элемент из коллекции по его ID");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду.
     * Принимает аргумент команды как ID.
     * Если в коллекции существует элемент с данным ID, удаляет его.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            if (collectionManager.getCollectionSize() == 0) throw new CollectionIsEmptyException();
            Integer id = Integer.parseInt(argument);
            if (collectionManager.getById(id) == null) throw new NoSuchElementException();
            collectionManager.removeById(id);
            System.out.println("Элемент удален!");
            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " должна принимать в качестве аргумента ID фильма");
            return false;
        }
        catch (CollectionIsEmptyException e) {
            System.err.println("Коллекция пуста");
            return false;
        }
        catch (NoSuchElementException e){
            System.err.println("Элемента с таким ID в коллекции нет");
            return false;
        }
    }
}
