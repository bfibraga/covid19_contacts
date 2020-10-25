package User;

/**
 * An interface that represents a <code>"Utilizador"</code> of the system.
 */
public interface User {

    /**
     * Returns the data of the user. The data includes the login, name, age, address and profession of the current <code>User</code>.
     *
     * @return Data of the current <code>User</code>.
     */
    //UserData getData();

    /**
     * Verify if the contact has been removed or not of the current <code>User</code> by other resgisted <code>User</code>
     *
     * @param toBeRemoved - The contact to be removed
     * @return
     */
    boolean removeContact(User toBeRemoved);

    /**
     * @param current
     * @return
     */
    boolean hasContactWith(User current);
}
