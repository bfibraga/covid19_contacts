package network.Group;

import network.Message.Message;
import network.User.*;
import network.dataStructures.Iterator;

/**
 * An interface that represents a <code>Group</code> of <code>Users</code> in the system
 * @author 57747_57833
 */
public interface Group extends GroupData {

    /**
     * Checks if this group has participants or not
     *
     * @return <color=green>True</color> - if this group has participant
     * <color=red>False</color> - if this group don't have any participants
     */
    boolean hasParticipants();

    /**
     * Returns a <code>Iterator</code> of <code>User</code> to list all the participants of this group
     *
     * @return A <code>Iterator</code> of type <code>User</code>
     */
    Iterator<? extends UserData> participantsIterator();

    /**
     * Removes the subscription of <code>user</code> from this group
     *
     * @param user - A user to remove his subscription of this group
     * @pre: user != null
     */
    void removeSubscription(User user);

    /**
     * Removes all users who are participants of this group
     */
    void removeAllParticipants();

    /**
     * Adds a new message to share with all participants of this group
     *
     * @param msg - A message to be added
     * @pre: msg != null
     */
    void insertMessage(Message msg);

    /**
     * Adds a new participant to this group
     *
     * @param user - A user to enter this group as a new participant
     * @pre: user != null
     */
    void addSubscription(User user);

    /**
     * Returns a <code>Iterator</code> of type <code>Message</code> to list all messages shared by all participants of this group
     *
     * @return A <code>Iterator</code> of type <code>Message</code>
     */
    Iterator<Message> listMessages();
}
