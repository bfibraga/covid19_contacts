package network.User;

import network.dataStructures.*;
import network.Exceptions.SubscriptionNotExists;
import network.Group.*;
import network.Message.*;

/**
 * A class that represents a <code>User</code>
 * @author 57747_57833
 */
public class UserClass implements User {

    /**
     * Maximum number of groups per user
     */
    private static final int MAX_GROUP_SIZE = 10;

    /**
     * Login of the user as <code>String</code>
     */
    private String login;

    /**
     * Name of this user as <code>String</code>
     */
    private String name;

    /**
     * Age of this user as <code>Integer</code>
     */
    private int age;

    /**
     * Address of this user as <code>String</code>
     */
    private String address;

    /**
     * Profession of this user as <code>String</code>
     */
    private String profession;

    /**
     * Collection of contacts with login as key and user as value
     */
    private OrderedDictionary<String, User> contacts;

    /**
     * Collection of groups with group name as key and group as value
     */
    private Dictionary<String, Group> groups;

    /**
     * Collection of all messages sent and/or received
     */
    private List<Message> messages;

    /**
     * <bold>Constructor:</bold> an given login, name, address and profession as <code>String</code> and a age as <code>Integer</code>.
     * @param login A string to login
     * @param name User's name
     * @param age User's age
     * @param address Address of this user
     * @param profession Profession that this user possesses
     */
    public UserClass(String login, String name, int age, String address, String profession) {
        this.login = login;
        this.name = name;
        this.age = age;
        this.address = address;
        this.profession = profession;
        contacts = new AVLTree<>();
        groups = new ChainedHashTable<>(MAX_GROUP_SIZE);
        messages = new SinglyLinkedList<>();
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getProfession() {
        return profession;
    }

    @Override
    public void addContact(User toBeInserted) {
        contacts.insert(toBeInserted.getLogin(), toBeInserted);
    }

    @Override
    public boolean hasContacts() {
        return !contacts.isEmpty();
    }

    @Override
    public Iterator<? extends UserData> contactIterator() {
        return contacts.iteratorValue();
    }

    @Override
    public boolean canJoinGroup() {
        return groups.size() != 10;
    }

    @Override
    public void subscribe(Group group){
        if(canJoinGroup()) groups.insert(group.getName(), group);
    }

    @Override
    public boolean hasSubscription(Group selected_group) {
        return groups.find(selected_group.getName()) != null;
    }

    @Override
    public void removeSubscription(Group group) {
        if (groups.find(group.getName()) == null)
            throw new SubscriptionNotExists();
        groups.remove(group.getName());
    }

    @Override
    public void createMessage(Message msg) {
        this.insertMessage(msg);
        Iterator<Group> itGroup = groups.iteratorValue();
        while(itGroup.hasNext()){
            Group group = itGroup.next();
            group.insertMessage(msg);
        }

        Iterator<User> itContacts = contacts.iteratorValue();
        while(itContacts.hasNext()){
            User user = itContacts.next();
            user.insertMessage(msg);
        }
    }

    @Override
    public boolean removeContact(User toBeRemoved) {
        return contacts.remove(toBeRemoved.getLogin()) != null;
    }

    @Override
    public boolean hasContactWith(User current) {
        return this.equals(current) || contacts.find(current.getLogin()) != null;
    }

    @Override
    public void insertMessage(Message msg){
        messages.addFirst(msg);
    }

    @Override
    public Iterator<Message> messageIterator(){
        return messages.iterator();
    }

    @Override
    public int compareTo(User o) {
        return this.getLogin().compareTo(o.getLogin());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof User)) return false;
        User otherUser = (User) other;
        return this.getLogin().equals(otherUser.getLogin());
    }
}