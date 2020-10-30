package network.Group;

import network.User.*;
import network.dataStructures.*;
import network.Message.*;

/**
 * An class that represents a <code>"Grupo"</code>.
 * @author 57747_57833
 */
public class GroupClass implements Group {

    /**
     * Name of this group as <code>String</code>
     */
    private String name;

    /**
     * Description of this group as <code>String</code>
     */
    private String description;

    /**
     * Collection of <code>Users</code> that represents all the participants of this group and saved in the contact network.
     * We chose to use an OrderedSequence, which has a DoubleLinkedList, because this collection needed to be fast on
     * inserting new elements, removing them and sorting the elements by the login of the <code>User</code> lexicographically.
     * Insertion and remove, best case scenario, take O(1) time to insert/remove (when on the first position).
     * Worst case is O(n) time (when on the last position to insert/remove).
     */
    private OrderedSequence<User> participants;

    /**
     * Collection of <code>Messages</code> that contains all the messages by the users saved on the collection above.
     * We chose to use an DoubleLinkedList because this collection needed to be fast on
     * inserting new elements, removing them and list all the messages in order of insertion.
     * Insertion and remove, best case scenario, take O(1) time to insert/remove (when on the first position).
     * Worst case is O(n) time (when on the last position to insert/remove).
     */
    private DoublyLinkedList<Message> messages;

    public GroupClass(String name, String text) {
        this.name = name;
        this.description = text;
        participants = new OrderedSequenceClass<User>();
        messages = new DoublyLinkedList<Message>();
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean hasParticipants() {
        return !participants.isEmpty();
    }

    @Override
    public Iterator<User> participantsIterator() {
        return participants.iterator();
    }

    @Override
    public boolean hasSubscription(User user) {
        return participants.contains(user);
    }

    @Override
    public void removeSubscription(User user) {
        participants.remove(user);
    }

    @Override
    public void removeAllParticipants() {

        Iterator<User> subscribers = participants.iterator();
        while (subscribers.hasNext()) {
            User user = subscribers.next();
            user.removeSubscription(this);
        }

    }

    @Override
    public void insertMessage(Message msg) {
        messages.addFirst(msg);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Group))
            return false;
        Group otherGroup = (Group) obj;
        return this.getName().equals(otherGroup.getName());
    }

    @Override
    public void addSubscription(User user) {
        participants.insert(user);
    }

    @Override
    public Iterator<Message> listMessages() {
        return messages.iterator();
    }


}