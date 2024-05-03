package commands;

import exceptions.WrongNumberOfArgumentsException;
/**
 * Класс команды, отвечающей за вывод справки.
 */
public class HelpCommand extends AbstractCommand{
    /**
     * Создает команду справки.
     */
    public HelpCommand() {
        super("help", "Вывести справку по доступным командам");
    }
    /**
     * Выполняет команду.
     * @param argument аргумент команды
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            return true;
        } catch (WrongNumberOfArgumentsException exception) {
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов!");
        }
        return false;
    }
}
