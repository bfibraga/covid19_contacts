package ContactNet;

import Exceptions.*;
import Group.Group;
import Message.Message;
import User.*;
import dataStructures.Iterator;
//Completar package e imports

public interface ContactNet {


    void insertUser(String login, String name, int age, String address, String profession)
            throws UserExists;

	User showUser(String login) throws UserNotExists;

    void insertContact(String login1, String login2)
            throws UserNotExists, ContactExists;

    void removeContact(String login1, String login2)
            throws UserNotExists, ContactNotExists, ContactNotRemoved;


    Iterator<User> listContacts(String login)
            throws UserNotExists, NoContacts;

    void insertGroup(String group, String description) throws GroupExists;

	Group showGroup(String group) throws GroupNotExists;

    void removeGroup(String group) throws GroupNotExists;

    void subscribeGroup(String login, String group)
            throws UserNotExists, GroupNotExists, SubscriptionExists;

    void removeSubscription(String login, String group)
            throws UserNotExists, GroupNotExists, SubscriptionNotExists;


    Iterator<User> listParticipants(String group)
            throws GroupNotExists, NoParticipants;

    void insertMessage(String login, String title, String text, String url)
            throws UserNotExists;

    Iterator<Message> listContactMessages(String login1, String login2)
            throws UserNotExists, ContactNotExists, NoContactMessages;

    Iterator<Message> listGroupMessages(String group, String login)
            throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;
}
