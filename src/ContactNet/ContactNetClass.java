package ContactNet;

import Exceptions.*;
import Group.Group;
import User.*;
import Message.*;
import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;

public class ContactNetClass implements ContactNet {

    private DoublyLinkedList<User> users;

    public ContactNetClass(){
        users = new DoublyLinkedList<User>();
    }

    @Override
    public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
        if(userExists(login)) throw new UserExists(); //if 
        User user = new UserClass(login, name, age, address, profession);
        users.addFirst(user);
    }

    @Override
    public User showUser(String login) throws UserNotExists {
        return null;
    }

    /**
     * Searchs throughout the list of users for an user that has the same login as the one given
     * @param login Login of the user to be found
     * @return true if an user with the same login exists, false if otherwise
     * @pre login != null
     */
    private boolean userExists(String login) {
        boolean found = false;
        User toBeFound = new UserClass(login, null, -1, null, null); // we create an user with
        // the same login and other paramenters are irrelevant as our equals() will only care about its login

        Iterator<User> it = users.iterator();
        while(it.hasNext() && !found) { // We go throughout the list until we either find the user with the same login or
                                        // we run out of elements on the list
            User user = it.next();
            if(user.equals(toBeFound)) found = true; // if the user has the same login as the one given, we found our user
                                                    // and we can leave the cycle
        }
        return found;
    }

    @Override
    public void insertContact(String login1, String login2) throws UserNotExists, ContactExists {

    }

    @Override
    public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {

    }

    @Override
    public Iterator<User> listContacts(String login) throws UserNotExists, NoContacts {
        return null;
    }

    @Override
    public void insertGroup(String group, String description) throws GroupExists {

    }

    @Override
    public Group showGroup(String group) throws GroupNotExists {
        return null;
    }

    @Override
    public void removeGroup(String group) throws GroupNotExists {

    }

    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {

    }

    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {

    }

    @Override
    public Iterator<User> listParticipants(String group) throws GroupNotExists, NoParticipants {
        return null;
    }

    @Override
    public void insertMessage(String login, String title, String text, String url) throws UserNotExists {

    }

    @Override
    public Iterator<Message> listContactMessages(String login1, String login2) throws UserNotExists, ContactNotExists, NoContactMessages {
        return null;
    }

    @Override
    public Iterator<Message> listGroupMessages(String group, String login) throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
        return null;
    }
}
