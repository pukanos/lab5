package commands;

import exceptions.WrongNumberOfArgumentsException;
/**
 * Класс команды, отвечающей за вывод истории использованных команд.
 */
public class HistoryCommand extends AbstractCommand{
    /**
     * Создает команду истории.
     */
    public HistoryCommand() {
        super("history", "Вывести последние 12 команд");
    }
    /**
     * Выполняет команду.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов");
            return false;
        }
    }
}
