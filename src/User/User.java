package User;

import dataStructures.Iterator;

/**
 * An interface that represents a <code>"Utilizador"</code> of the system.
 */
public interface User extends UserData, Comparable<User> {

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

    void addContact(User new_contact);

    boolean hasContacts();

    Iterator<User> contactIterator();
}
