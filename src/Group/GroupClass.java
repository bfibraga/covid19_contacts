package Group;

import User.*;
import dataStructures.*;
import Message.*;

/**
 * An class that represents a <code>"Grupo"</code>.
 */
public class GroupClass implements Group{

    private String name;
    private String description;
    private OrderedSequence<User> participants;
    private DoublyLinkedList<Message> messages;

    public GroupClass(String name, String text){
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
    public void addSubscription(User user) {
        participants.insert(user);
    }

    @Override
    public void removeSubscription(User user) {
        participants.remove(user);
    }

    @Override
    public void removeAllParticipants() {

        Iterator<User> subscribers = participants.iterator();
        while(subscribers.hasNext()){
            User user = subscribers.next();
            user.removeSubscription(this);
        }
    }

    @Override
    public void addGroupMessage(Message message) {
        messages.addLast(message);
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


}
