package network.ContactNet;

import network.Exceptions.*;
import network.Group.Group;
import network.Message.Message;
import network.User.*;
import network.dataStructures.Iterator;
//Completar package e imports

/**
 * An top interface that contains all the available commands in <code>Main</code>
 * @author 57747_57833
 */
public interface ContactNet {


    /**
     * Inserts an user within the contact network, with a given login, name, age, address and profession, to the user collection.
     * @param login Login of the new user
     * @param name Name of the new user
     * @param age Age of the new user
     * @param address Address of the new user
     * @param profession Profession of the new user
     * @throws UserExists when an user with the same login exists in the network already
     * @pre login != null && name != null && age > -1 && address != null && profession != null
     */
    void insertUser(String login, String name, int age, String address, String profession)
            throws UserExists;

    /**
     * Checks throughout the collection of users in search of an user with given login.
     * @param login Login of the user we are looking for
     * @return the user with same loging as the one provided
     * @throws UserNotExists when there isn't an user in the collection with same login as the one provided
     * @pre login != null
     */
	User showUser(String login) throws UserNotExists;

    /**
     * Inserts a contact between two users identified by their logins, where each can see the other's messages.
     * @param login1 Login of the first user
     * @param login2 Login of the second user
     * @throws UserNotExists when one of the users dont exist in the contact network
     * @throws ContactExists when they are already contacts between each other
     * @pre login1 != null && login2 != null
     */
    void insertContact(String login1, String login2)
            throws UserNotExists, ContactExists;

    /**
     * Removes a contact between two users identified by their respective logins, removing them the ability of seeing
     * the other's messages
     * @param login1 Login of the first user
     * @param login2 Login of the second user
     * @throws UserNotExists when one of the users dont exist in the contact network
     * @throws ContactNotExists when they dont have a contact of each other
     * @throws ContactNotRemoved when both the users are the same one
     * @pre login1 != null && login2 != null
     */
    void removeContact(String login1, String login2)
            throws UserNotExists, ContactNotExists, ContactNotRemoved;


    /**
     * Lists all contacts that an user with a given login has.
     * @param login Login of the user
     * @return An iterator that iterates throughout the user's contacts
     * @throws UserNotExists when the user doesn't exist in the contact network
     * @throws NoContacts when the user doesn't have any contacts to list
     * @pre login != null
     */
    Iterator<User> listContacts(String login)
            throws UserNotExists, NoContacts;

    /**
     * Inserts a group into the contact network, with a given name and description of the group.
     * @param group Name of the group
     * @param description Description of the group
     * @throws GroupExists when a group with the same name already exists in the network
     * @pre group != null && description != null
     */
    void insertGroup(String group, String description) throws GroupExists;

    /**
     * Searches around the group collection for a group that has the same name as the one provided.
     * @param group Name of the group we are looking for
     * @return the group that has the same name as the one provided
     * @throws GroupNotExists when the group isn't present in the collection
     * @pre group != null
     */
	Group showGroup(String group) throws GroupNotExists;

    /**
     * Removes a group with given name from the collection of groups present in the contact network
     * @param group Name of the group we wish to remove
     * @throws GroupNotExists when the group with that name doesn't exist in the collection
     * @pre group != null
     */
    void removeGroup(String group) throws GroupNotExists;

    /**
     * Makes a subscription from an user with given login to a group with given name, making them able to send messages
     * there and look through the group's messages.
     * @param login Login of the user that wants to subscribe to the group
     * @param group Name of the group that the user wishes to enter
     * @throws UserNotExists when the user doesn't exist in the network
     * @throws GroupNotExists when the group doesn't exist in the network
     * @throws SubscriptionExists when the user already subscribed to the group
     * @pre login != null && group != null
     */
    void subscribeGroup(String login, String group)
            throws UserNotExists, GroupNotExists, SubscriptionExists;

    /**
     * Removes a subscription made by an user with given name from a group with given name, which they won't be able to
     * look through the group's messages and send messages there afterwards unless they subscribe again.
     * @param login Login of the user that wishes to remove the subscription
     * @param group Name of the group the user wishes to leave
     * @throws UserNotExists when the user doesn't exist in the contact network
     * @throws GroupNotExists when the group doesn't exist in the network
     * @throws SubscriptionNotExists when the user doesn't belong to the group already
     * @pre login != null && group != null
     */
    void removeSubscription(String login, String group)
            throws UserNotExists, GroupNotExists, SubscriptionNotExists;


    /**
     * Lists all participants that a group with given name has at the time this function is called.
     * @param group Name of the group
     * @return An iterator that iterates throughout the list of users that the group has
     * @throws GroupNotExists when the group doesn't exist in the network
     * @throws NoParticipants when the group doesn't have any users subscribed to it
     * @pre group != null
     */
    Iterator<User> listParticipants(String group)
            throws GroupNotExists, NoParticipants;

    /**
     * Inserts a message with given title, text and url and whose author is an user with given login. Afterwards, it will
     * be sent to all the user's contacts and groups in which the user is subscribed to.
     * @param login Login of the user that is creating a message
     * @param title Title of the message
     * @param text Text of the message
     * @param url URL of the message
     * @throws UserNotExists when the user doesn't exist in the network
     * @pre login != null && title != null && text != null && url != null
     */
    void insertMessage(String login, String title, String text, String url)
            throws UserNotExists;

    /**
     * Lists all messages from a contact with given login which is requested to list by another user with given login.
     * The user can request to check their own messages so it wont fail when the users asks to check their own messages.
     * @param login1 Login of the first user
     * @param login2 Login of the second user
     * @return An iterator that iterates throughout the messages of the first user, wether they were sent or received by them
     * @throws UserNotExists when one of the users doesn't exist in the network
     * @throws ContactNotExists when the users aren't contacts of one another
     * @throws NoContactMessages when the first user doesn't have any messages to list
     * @pre login1 != null && login2 != null
     */
    Iterator<Message> listContactMessages(String login1, String login2)
            throws UserNotExists, ContactNotExists, NoContactMessages;

    /**
     *  Lists all messages from a group with given name, requested by an user with given login and is subscribed to that
     *  group.
     * @param group Name of the group from where we will get the messages
     * @param login Login of the user requesting access to the group's messages
     * @return An iterator that iterates through the group's messages
     * @throws GroupNotExists when the group doesn't exist in the network
     * @throws UserNotExists when the user doesn't exist in the network
     * @throws SubscriptionNotExists when user isn't subscribed to the group
     * @throws NoGroupMessages when the group doesn't have any messages to list
     * @pre group != null && login != null
     */
    Iterator<Message> listGroupMessages(String group, String login)
            throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;
}
