package Group;

import Message.Message;
import User.*;
import dataStructures.Iterator;

public interface Group extends GroupData{

    boolean hasParticipants();

    Iterator<User> participantsIterator();

    boolean hasSubscription(User user);

    void removeSubscription(User user);

    void removeAllParticipants();

    void insertMessage(Message msg);

    void addSubscription(User user);

    Iterator<Message> listMessages();
}
