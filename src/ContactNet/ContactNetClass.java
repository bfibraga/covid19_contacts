package ContactNet;

import Exceptions.*;
import Group.*;
import User.*;
import Message.*;
import dataStructures.*;

public class ContactNetClass implements ContactNet {

    private OrderedSequence<User> users;
    private DoublyLinkedList<Group> groups;

    public ContactNetClass(){
        users = new OrderedSequenceClass<User>();
        groups = new DoublyLinkedList<Group>();
    }

    @Override
    public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
        if(userExists(login)) throw new UserExists(); //if 
        User user = new UserClass(login, name, age, address, profession);
        users.insert(user);
    }

    @Override
    public User showUser(String login) throws UserNotExists {
        User toBeFound = new UserClass(login, null, -1, null, null);
        User found = users.get(toBeFound);

        if (found == null) throw new UserNotExists();
        return found;

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
        User user1 = showUser(login1);
        User user2 = showUser(login2);
        if(user1.hasContactWith(user2)) throw new ContactExists();

        user1.addContact(user2);
        user2.addContact(user1);
    }

    @Override
    public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {

            User user1 = showUser(login1);
            User user2 = showUser(login2);
            if (user1 == null || user2 == null) throw new UserNotExists();
            if (user1.equals(user2)) throw new ContactNotRemoved();
            if (!user1.hasContactWith(user2)) throw new ContactNotExists();

            user1.removeContact(user2);
            user2.removeContact(user1);

    }



    @Override
    public Iterator<User> listContacts(String login) throws UserNotExists, NoContacts {
        User user = showUser(login);
        if(user == null) throw new UserNotExists();
        if(!user.hasContacts()) throw new NoContacts();

        return user.contactIterator();

    }

    @Override
    public void insertGroup(String group, String description) throws GroupExists {
        Group result = searchGroup(group);
        if(result != null) throw new GroupExists();
        groups.addFirst(new GroupClass(group, description));
    }

    @Override
    public Group showGroup(String group) throws GroupNotExists {

        Group result = searchGroup(group);
        if(result == null) throw new GroupNotExists();
        return result;

    }

    @Override
    public void removeGroup(String group) throws GroupNotExists {
        Group toBeRemoved = showGroup(group);
        toBeRemoved.removeAllParticipants();
        groups.remove(toBeRemoved);
    }

    private Group searchGroup(String group){
        Iterator<Group> it = groups.iterator();
        Group template = new GroupClass(group, null);
        Group result;
        while(it.hasNext()){
            result = it.next();
            if(result.equals(template)) return result;
        }

        return null;

    }

    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {
        User selected_user = showUser(login);
        Group selected_group = showGroup(group);

        //TODO Improve time c. O(n^2)
        if (selected_group.hasSubscription(selected_user))
            throw new SubscriptionExists();
        selected_group.addSubscription(selected_user);
        selected_user.subscribe(selected_group);
    }

    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {
        User user = showUser(login);
        Group groupToSubscribe = showGroup(group);
        if(!groupToSubscribe.hasSubscription(user)) throw new SubscriptionNotExists();

        groupToSubscribe.removeSubscription(user);
        user.removeSubscription(groupToSubscribe);

    }

    @Override
    public Iterator<User> listParticipants(String group) throws GroupNotExists, NoParticipants {
        Group result = showGroup(group);
        if(!result.hasParticipants()) throw new NoParticipants();
        return result.participantsIterator();
    }

    @Override
    public void insertMessage(String login, String title, String text, String url) throws UserNotExists {
        User author = showUser(login);
        Message msg = new MessageClass(title, text, url);
        author.createMessage(msg);
    }

    @Override
    public Iterator<Message> listContactMessages(String login1, String login2) throws UserNotExists, ContactNotExists, NoContactMessages {
        User user1 = showUser(login1);
        User user2 = showUser(login2);
        if(!user1.hasContactWith(user2)) throw new ContactNotExists();
        Iterator<Message> it = user1.messageIterator();
        if(!it.hasNext()) throw new NoContactMessages();
        return it;
    }

    @Override
    public Iterator<Message> listGroupMessages(String group, String login) throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
        Group g = showGroup(group);
        User user = showUser(login);
        if(!g.hasSubscription(user)) throw new SubscriptionNotExists();
        Iterator<Message> it = g.listMessages();
        if(!it.hasNext()) throw new NoGroupMessages();
        return it;
    }
}
