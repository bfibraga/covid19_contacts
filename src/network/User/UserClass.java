package network.User;

import network.Group.*;
import network.Message.*;
import network.dataStructures.DoublyLinkedList;
import network.dataStructures.Iterator;
import network.dataStructures.OrderedSequence;
import network.dataStructures.OrderedSequenceClass;

/**
 * A class that represents a <code>User</code>
 * @author 57747_57833
 */
public class UserClass implements User {
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
     * Collection of <code>Users</code> that represents all the contacts of this user saved in the contact network.
     * We chose to use an OrderedSequence, which has a DoubleLinkedList, because this collection needed to be fast on
     * inserting new elements, removing them and sorting the elements by the login of the <code>User</code> lexicographically
     * in the command <bold>LC</bold> (listar contactos).
     * Insertion and remove, best case scenario, take O(1) time to insert/remove (when on the first position).
     * Worst case is O(n) time (when on the last position to insert/remove).
     */
    private OrderedSequence<User> contacts;

    /**
     * Collection of groups that is saved in this user.
     * It was chosen to use a DoublyLinkedList data structure as they will be a lot of inserts of groups (and possible
     * removals). Insertion and remove, best case scenario, take O(1) time to insert/remove (when on the first position).
     * Worst case is O(n) time (when on the last position to insert/remove).
     * We didn't consider using an array to save the groups for the same reason mentioned above.
     */
    private DoublyLinkedList<Group> groups;

    /**
     * Collection of messages that is saved in this user.
     * It was chosen to use a DoublyLinkedList data structure as they will be a lot of inserts of groups (and possible
     * removals). Insertion and remove, best case scenario, take O(1) time to insert/remove (when on the first position).
     * Worst case is O(n) time (when on the last position to insert/remove).
     * We didn't consider using an array to save the groups for the same reason mentioned above.
     */
    private DoublyLinkedList<Message> messages;

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
        contacts = new OrderedSequenceClass<User>();
        groups = new DoublyLinkedList<Group>();
        messages = new DoublyLinkedList<Message>();
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
        contacts.insert(toBeInserted);
    }

    @Override
    public boolean hasContacts() {
        return !contacts.isEmpty();
    }

    @Override
    public Iterator<? extends UserData> contactIterator() {
        return contacts.iterator();
    }

    @Override
    public boolean canJoinGroup() {
        return groups.size() != 10;
    }

    @Override
    public void subscribe(Group group){
        if(canJoinGroup()) groups.addFirst(group);
    }

    @Override
    public void removeSubscription(Group group) {
        groups.remove(group);
    }

    @Override
    public void createMessage(Message msg) {
        this.insertMessage(msg);
        Iterator<Group> itGroup = groups.iterator();
        while(itGroup.hasNext()){
            Group group = itGroup.next();
            group.insertMessage(msg);
        }

        Iterator<User> itContacts = contacts.iterator();
        while(itContacts.hasNext()){
            User user = itContacts.next();
            user.insertMessage(msg);
        }
    }

    @Override
    public boolean removeContact(User toBeRemoved) {
        return contacts.remove(toBeRemoved);
    }

    @Override
    public boolean hasContactWith(User current) {
        return this.equals(current) || contacts.contains(current);
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