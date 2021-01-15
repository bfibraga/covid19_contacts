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
     *Collection that contains all the user participating on this group
     */
    private OrderedDictionary<String, User> participants;

    /**
     * Collection that contains all the messages by the users saved on the collection above.
     */
    private List<Message> messages;

    /**
     *  <bold>Constructor:</bold> initialize a group name and its description as <code>String</code>.
     * @param name - Name of the group
     * @param text - Description of the group
     */
    public GroupClass(String name, String text) {
        this.name = name;
        this.description = text;
        participants = new AVLTree<>();
        messages = new SinglyLinkedList<>();
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
        return participants.iteratorValue();
    }

    @Override
    public void removeSubscription(User user) {
        participants.remove(user.getLogin());
    }

    @Override
    public void removeAllParticipants() {

        Iterator<User> subscribers = participants.iteratorValue();
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
        participants.insert(user.getLogin(), user);
    }

    @Override
    public Iterator<Message> listMessages() {
        return messages.iterator();
    }


}