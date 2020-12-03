package network.ContactNet;

import network.Exceptions.*;
import network.Group.*;
import network.User.*;
import network.Message.*;
import dataStructures.*;

public class ContactNetClass implements ContactNet {

    /**
     * TODO
     */
    private Dictionary<String, User> users;

    /**
     * TODO
     */
    private Dictionary<String, Group> groups;

    /**
     * TODO
     */
    public ContactNetClass(){
        users = new ChainedHashTable<>();
        groups = new ChainedHashTable<>();
    }

    @Override
    public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
        if(userExists(login)) throw new UserExists();
        User user = new UserClass(login, name, age, address, profession);
        users.insert(login, user);
    }

    @Override
    public User showUser(String login) throws UserNotExists {
        User found = users.find(login);
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
        return users.find(login) != null;
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
            if (user1.equals(user2)) throw new ContactNotRemoved();
            if (!user1.hasContactWith(user2)) throw new ContactNotExists();

            user1.removeContact(user2);
            user2.removeContact(user1);

    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<UserData> listContacts(String login) throws UserNotExists, NoContacts {
        User user = showUser(login);
        if(user == null) throw new UserNotExists();
        if(!user.hasContacts()) throw new NoContacts();

        return (Iterator<UserData>) user.contactIterator();

    }

    @Override
    public void insertGroup(String group, String description) throws GroupExists {
        Group result = searchGroup(group);
        if(result != null) throw new GroupExists();
        groups.insert(group, new GroupClass(group, description));
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
        groups.remove(group);
    }

    /**
     * Searches around the collection of groups in search of a group with a given name.
     * @param group Name of the group that we're searching for
     * @return A group that is present in the collection and has the same name as the one provided.
     * @pre group != null
     */
    private Group searchGroup(String group){
        return groups.find(group);
    }

    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {
        User selected_user = showUser(login);
        Group selected_group = showGroup(group);


        if (selected_user.hasSubscription(selected_group))
            throw new SubscriptionExists();
        selected_group.addSubscription(selected_user);
        selected_user.subscribe(selected_group);
    }

    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {
        User user = showUser(login);
        Group groupToSubscribe = showGroup(group);
        user.removeSubscription(groupToSubscribe);
        groupToSubscribe.removeSubscription(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<UserData> listParticipants(String group) throws GroupNotExists, NoParticipants {
        Group result = showGroup(group);
        if(!result.hasParticipants()) throw new NoParticipants();
        return (Iterator<UserData>) result.participantsIterator();
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
        if(!user.hasSubscription(g)) throw new SubscriptionNotExists();
        Iterator<Message> it = g.listMessages();
        if(!it.hasNext()) throw new NoGroupMessages();
        return it;
    }
}
