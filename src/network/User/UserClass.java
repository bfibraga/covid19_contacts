package network.User;

import network.Group.*;
import network.Message.*;
import network.dataStructures.DoublyLinkedList;
import network.dataStructures.Iterator;
import network.dataStructures.OrderedSequence;
import network.dataStructures.OrderedSequenceClass;

/**
 * A class that
 */
public class UserClass implements User {

    private String login;
    private String name;
    private int age;
    private String address;
    private String profession;
    private OrderedSequence<User> contacts;
    private DoublyLinkedList<Group> groups;
    private DoublyLinkedList<Message> messages;

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
    public Iterator<User> contactIterator() {
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
