package commands;

import java.util.Objects;
/**
 * Абстрактный класс-предок для всех команд
 */
public abstract class AbstractCommand implements ICommand{
    private final String commandName;
    private final String commandDescription;
    /**
     * Возвращает название команды
     * @return название команды
     */
    public String getCommandName() {
        return commandName;
    }
    /**
     * Возвращает описание команды
     * @return описание команды
     */
    public String getCommandDescription() {
        return commandDescription;
    }
    /**
     * Конструктор абстрактной команды
     * @param commandName название команды
     * @param commandDescription описание команды
     */
    public AbstractCommand(String commandName, String commandDescription){
        this.commandName = commandName;
        this.commandDescription = commandDescription;
    }
    /**
     * Возвращает название и описание команды
     * @return название и описание команды
     */
    @Override
    public String toString(){
        return commandName + ": " + commandDescription;
    }
    /**
     * Возвращает hashCode, основанный на названии и описании команды
     * @return hashCode
     */
    @Override
    public int hashCode(){
        return Objects.hash(commandName, commandDescription);
    }
    /**
     * Сравнивает две команды по их hashCode
     * @return true, если команды одинаковы, false - иначе
     */
    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractCommand abstractCommand = (AbstractCommand) object;
        return hashCode() == abstractCommand.hashCode();
    }
}
