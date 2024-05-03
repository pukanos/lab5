import commands.*;
import managers.*;

import java.util.Scanner;
/**
 * Основной класс программы.
 */
public class Main {
    /**
     * Создает нужные для работы утилиты.
     * После этого переводит программу в интерактивный режим.
     * @param args аргументы, вводимые пользователем в командной строке при запуске программы
     */
    public static void main(String[] args) {
        FileManager fileManager = null;
        if (args.length == 0) {
            fileManager = new FileManager("data.csv");
        } else if (args.length > 1) {
            System.err.println("Введите 1 аргумент! Вы ввели: " + args.length);
            System.exit(1);
        } else {
            fileManager = new FileManager(args[0]);
        }
        System.out.println("Начало работы");
        Scanner scanner = new Scanner(System.in);
        MovieCreator movieCreator = new MovieCreator(scanner);
        CollectionManager collectionManager = new CollectionManager(fileManager);

        CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new UpdateCommand(movieCreator, collectionManager),
                new AddCommand(collectionManager, movieCreator),
                new ExitCommand(),
                new HistoryCommand(),
                new RemoveByIdCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ShowCommand(collectionManager),
                new ClearCommand(collectionManager),
                new PrintAscendingCommand(collectionManager),
                new PrintUniqueOscarsCountCommand(collectionManager),
                new FilterLessThanDirectorCommand(collectionManager),
                new RemoveGreaterCommand(collectionManager),
                new AddIfMaxCommand(collectionManager, movieCreator),
                new ExecuteScriptCommand()
        );
        InputManager inputManager = new InputManager(commandManager,scanner,movieCreator);
        inputManager.interactiveMode();
    }
}
