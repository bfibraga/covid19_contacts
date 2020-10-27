package ContactNet;

import Exceptions.*;
import Group.*;
import User.*;
import Message.*;
import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.OrderedSequence;
import dataStructures.OrderedSequenceClass;

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
        User user1 = new UserClass(login1, null, -1, null, null);
        User user2 = new UserClass(login2, null, -1, null, null);
        user1 = users.get(user1);
        user2 = users.get(user2);
        if(user1 == null || user2 == null) throw new UserNotExists();
        if(user1.hasContactWith(user2) || user1.equals(user2)) throw new ContactExists();

        user1.addContact(user2);
        user2.addContact(user1);
    }

    @Override
    public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {
        if (login1.equals(login2))
            throw new ContactNotRemoved();
        else {
            User user1 = showUser(login1);
            User user2 = showUser(login2);
            if (user1 == null || user2 == null){
                throw new UserNotExists();
            } else if (!user1.hasContactWith(user2) || !user2.hasContactWith(user1)){
                throw new ContactNotRemoved();
            } else {
                user1.removeContact(user2);
                user2.removeContact(user1);
            }
        }
    }

    @Override
    public Iterator<UserData> listContacts(String login) throws UserNotExists, NoContacts {
        User user = users.get(new UserClass(login, null, -1, null, null));
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
        Group toBeRemoved = searchGroup(group);
        if(toBeRemoved == null) throw new GroupNotExists();
        toBeRemoved.removeAllParticipants();
        groups.remove(toBeRemoved);
    }

    private Group searchGroup(String group){
        Iterator<Group> it = groups.iterator();
        Group template = new GroupClass(group, null);
        boolean found = false;
        Group g = null;
        while(it.hasNext()){
            g = it.next();
            if (g.equals(template)) return g;
        }

        return null;
    }

    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {

    }

    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {
        User user = users.get(new UserClass(login, null, -1, null, null));
        if(user == null) throw new UserNotExists();
        Group groupToSubscribe = searchGroup(group);
        if(groupToSubscribe == null) throw new GroupNotExists();
        if(!groupToSubscribe.hasSubscription(user)) throw new SubscriptionNotExists();

        groupToSubscribe.removeSubscription(user);

    }

    @Override
    public Iterator<UserData> listParticipants(String group) throws GroupNotExists, NoParticipants {
        Group result = searchGroup(group);
        if (result == null) throw new GroupNotExists();
        if(!result.hasParticipants()) throw new NoParticipants();
        return result.participantsIterator();
    }

    @Override
    public void insertMessage(String login, String title, String text, String url) throws UserNotExists {
        User author = users.get(new UserClass(login, null, -1, null, null));
        if (author == null) throw new UserNotExists();
        Message msg = new MessageClass(title, text, url, author);
        author.createMessage(msg);
    }

    @Override
    public Iterator<Message> listContactMessages(String login1, String login2) throws UserNotExists, ContactNotExists, NoContactMessages {
        User user1 = users.get(new UserClass(login1, null, -1, null, null));
        User user2 = users.get(new UserClass(login2, null, -1, null, null));
        if (user1 == null || user2 == null) throw new UserNotExists();
        if(!user1.hasContactWith(user2)) throw new ContactNotExists();
        Iterator<Message> it = user1.messageIterator();
        if(!it.hasNext()) throw new NoContactMessages();
        return it;
    }

    @Override
    public Iterator<Message> listGroupMessages(String group, String login) throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
        return null;
    }
}
