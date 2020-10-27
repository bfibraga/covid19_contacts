package Group;

import Message.*;
import User.*;
import dataStructures.*;

public interface Group extends GroupData{

    boolean hasParticipants();

    Iterator<User> participantsIterator();

    boolean hasSubscription(User user);

    void addSubscription(User user);

    void removeSubscription(User user);

    void removeAllParticipants();

    void addGroupMessage(Message message);

}
