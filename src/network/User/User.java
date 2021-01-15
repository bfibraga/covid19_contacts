package network.User;

import network.Group.Group;
import network.Message.Message;
import network.dataStructures.Iterator;

/**
 * An interface that represents a <code>"Utilizador"</code> of the system.
 * @author 57747_57833
 */
public interface User extends UserData, Comparable<User>{

    /**
     * Verify if the contact has been removed or not of the current <code>network.User</code> by other resgisted <code>network.User</code>
     *
     * @param toBeRemoved - The contact to be removed
     * @return true if the contact was sucessfully removed from the contacts list, false if otherwise
     * @pre toBeRemoved != null && hasContactWith(toBeRemoved)
     */
    boolean removeContact(User toBeRemoved);

    /**
     * Checks if the user has a contact with another user.
     * @param current User that we're checking if they're a contact of this user
     * @return true if this user has a contact with this user, false if otherwise.
     * @pre current != null
     */
    boolean hasContactWith(User current);

    /**
     * Adds a contact with a given user.
     * @param new_contact User who will be a contact with this user
     * @pre new_contact != null && !hasContactWith(new_contact)
     */
    void addContact(User new_contact);

    /**
     * Checks if this user has any contacts with other users
     * @return true if the user has at least one contact, false if they have none.
     */
    boolean hasContacts();

    /**
     * Iterates throughout the list of users that are contacts with this user
     * @return An iterator that iterates throughout the user's contacts
     * @pre hasContacts();
     */
    Iterator<? extends UserData> contactIterator();

    /**
     * Checks if the user hasn't passed the 10 groups limit.
     * @return true if the user joined less than 10 groups, false if otherwise
     */
    boolean canJoinGroup();

    /**
     * Removes a subscription to a given group, causing the user to not be able to see its messages.
     * @param group Group from which the user wishes to leave
     * @pre group != null
     */
    void removeSubscription(Group group);

    /**
     * The user creates an message which is saved in their message list and sends that message to all their contacts and
     * groups that are subscribed to.
     * @param msg Message that was written by the user
     * @pre msg != null
     */
    void createMessage(Message msg);

    /**
     * Inserts a message received from a contact to their message list.
     * @param msg Message that was received by the user
     * @pre msg != null
     */
    void insertMessage(Message msg);

    /**
     * Iterates throughout the messages that were written or received by this user by insertion order, listing through
     * most recent to last.
     * @return Iterator that iterates through the user's messages
     */
    Iterator<Message> messageIterator();

    /**
     * Subscribes to a given group, giving access to its messages and able to send messages there.
     * @param group Group that the user wishes to enter
     * @pre group != null && canJoinGroup()
     */
    void subscribe(Group group);

    /**
     * Checks if the user has a subscription in the given group, checking their own subscriptions
     * @param selected_group Group where we are checking if they are in it or not
     * @return true if the group is in the subscription list, false if otherwise
     * @pre group != null
     */
    boolean hasSubscription(Group selected_group);
}