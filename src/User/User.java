package User;

/**
 * An interface that represents a <code>"Utilizador"</code> of the system.
 */
public interface User extends UserData, Comparable<User> {

    /**
     * Inserts a new user to the contact list of this user
     * @param toBeInserted the user that is to be inserted in the contact list
     * @pre toBeInserted != null
     */
    void addContact(User toBeInserted);

    /**
     * Verify if the contact has been removed or not of the current <code>User</code> by other resgisted <code>User</code>
     *
     * @param toBeRemoved - The contact to be removed
     * @return true if it has been removed successfully, false if otherwise
     */
    boolean removeContact(User toBeRemoved);

    /**
     * @param current
     * @return
     */
    boolean hasContactWith(User current);

}
