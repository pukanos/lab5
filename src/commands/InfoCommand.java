package commands;

import managers.CollectionManager;

import java.time.LocalDateTime;
/**
 * Класс команды, отвечающей за вывод информации о коллекции.
 */
public class InfoCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    /**
     * Создает команду вывода информации о коллекции.
     * @param movieCollection утилита для работы с коллекцией
     */
    public InfoCommand(CollectionManager movieCollection) {
        super("info", "Вывести информацию о коллекции");
        this.collectionManager = movieCollection;
    }
    /**
     * Выполняет команду.
     * Выводит тип, количество элементов и время последнего обновления коллекции, если она не пуста.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    public boolean execute(String argument){
        try {
            LocalDateTime lastUpdate = collectionManager.getLastUpdate();
            String lastUpdateString = (lastUpdate == null) ? "Данная коллекция еще не была обновлена" :
                    lastUpdate.toLocalTime().toString() + " " + lastUpdate.toLocalDate().toString();
            System.out.println("Информация о коллекции:");
            System.out.println("Тип: " + collectionManager.getCollectionType());
            System.out.println("Количество элементов: " + collectionManager.getCollectionSize());
            System.out.println("Время последнего обновления: " + lastUpdateString);
        }
        catch (NullPointerException e){
            System.err.println("Коллекция пуста!");
        }
        return true;
    }
}
