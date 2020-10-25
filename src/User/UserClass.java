package User;

import Group.Group;
import dataStructures.DoublyLinkedList;
import dataStructures.OrderedSequence;
import dataStructures.OrderedSequenceClass;

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

    public UserClass(String login, String name, int age, String address, String profession){
        this.login = login;
        this.name = name;
        this.age = age;
        this.address = address;
        this.profession = profession;
        contacts = new OrderedSequenceClass<User>();
        groups = new DoublyLinkedList<Group>();
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
    public boolean removeContact(User toBeRemoved) {
        return contacts.remove(toBeRemoved);
    }

    @Override
    public boolean hasContactWith(User current) {
        return false;
    }


    @Override
    public int compareTo(User o) {
        return getLogin().compareTo(o.getLogin());
    }
}
