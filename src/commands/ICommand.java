package commands;
/**
 * Интерфейс всех команд.
 */
public interface ICommand {

    /**
     * Выполняет команду.
     * @param argument аргумент команды
     * @return true, если команда была выполнена корректно, false - иначе
     */
    boolean execute(String argument);

    /**
     * Возвращает название команды.
     * @return название
     */
    String getCommandName();

    /**
     * Возвращает описание команды.
     * @return описание
     */
    String getCommandDescription();
}
