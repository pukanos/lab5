package commands;

import exceptions.WrongNumberOfArgumentsException;
import managers.CollectionManager;
/**
 * Класс команды, отвечающей за очищение коллекции.
 */
public class ClearCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду очищения коллекции.
     * @param collectionManager утилита для работы с коллекцией.
     */
    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "Очистить коллекцию");
        this.collectionManager = collectionManager;
    }
    /**
     * Выполняет команду очищения коллекции. Удаляет все элементы коллекции.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            collectionManager.clearCollection();
            System.out.println("Коллекция очищена!");
            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов");
            return false;
        }
    }
}
