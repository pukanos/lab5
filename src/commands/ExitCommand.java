package commands;

import exceptions.WrongNumberOfArgumentsException;
/**
 * Класс команды, отвечающей за завершение работы программы.
 */
public class ExitCommand extends AbstractCommand{
    /**
     * Создает команду завершения работы.
     */
    public ExitCommand(){
        super("exit", "Завершить программу(без сохранения)");
    }
    /**
     * Завершает работу программы, если выполнена корректно
     * @param argument аргумент команды.
     * @return true, если команда выполнена корректно, false - иначе
     */
    @Override
    public boolean execute(String argument){
        try {
            if (!argument.isEmpty()) throw new WrongNumberOfArgumentsException();
            System.out.println("Завершение работы");
            System.exit(0);
            return true;
        }
        catch (WrongNumberOfArgumentsException e){
            System.err.println("Команда " + getCommandName() + " принимает 0 аргументов"); // ???
            return false;
        }
    }
}
