package Group;

import User.User;
import dataStructures.Iterator;

public interface Group extends GroupData{

    boolean hasParticipants();

    Iterator<User> participantsIterator();

    boolean hasSubscription(User user);

    void removeSubscription(User user);

    void removeAllParticipants();
}
