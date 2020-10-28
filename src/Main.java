import network.ContactNet.*;
import network.Exceptions.*;
import network.Group.*;
import network.Message.Message;
import network.User.*;
import network.dataStructures.*;

import java.util.Scanner;

//TODO
//import *** OS VOSSOS IMPORTS AQUI ***
//EX: Se quiserem separar as excepcoes das interfaces e
//classes de dominio (cujo pacote se chama network neste exemplo):
//import network.*;
//import network.exceptions.*;

public class Main {

    private static final String PROMPT = "> ";

    //constantes para os comandos do enunciado
    //isto tambem podia ser feito com um enumerado
    private static final String INSERT_USER = "IU";
    private static final String SHOW_USER = "DU";
    private static final String INSERT_CONTACT = "IC";
    private static final String REMOVE_CONTACT = "RC";
    private static final String LIST_CONTACTS = "LC";
    private static final String INSERT_GROUP = "IG";
    private static final String SHOW_GROUP = "DG";
    private static final String REMOVE_GROUP = "RG";
    private static final String INSERT_GROUP_PARTICIPANT = "IP";
    private static final String REMOVE_GROUP_PARTICIPANT = "RP";
    private static final String LIST_GROUP_PARTICIPANTS = "LP";
    private static final String INSERT_MESSAGE = "IM";
    private static final String LIST_CONTACT_MESSAGES = "LMC";
    private static final String LIST_GROUP_MESSAGES = "LMG";
    private static final String EXIT = "FIM";

    //constantes para as mensagens de output
    //mensagens de comando cexecutado
    private static final String INSERT_USER_OK = "Registo de utilizador executado.";
    private static final String INSERT_CONTACT_OK = "Registo de contacto executado.";
    private static final String REMOVE_CONTACT_OK = "Remocao de contacto executada.";
    private static final String INSERT_GROUP_OK = "Registo de grupo executado.";
    private static final String REMOVE_GROUP_OK = "Remocao de grupo executada.";
    private static final String SUBSCRIBE_GROUP_OK = "Registo de participante executado.";
    private static final String REMOVE_SUBSCRIPTION_OK = "Remocao de aderencia executada.";
    private static final String INSERT_MESSAGE_OK = "Registo de mensagem executado.";
    private static final String EXIT_MESSAGE = "Obrigado. Ate a proxima.";
    //mensagens de comando nao executado - execucao sem sucesso
    public static final String USER_EXISTS = "Utilizador ja existente.";
    public static final String USER_NOT_EXISTS = "Inexistencia do utilizador referido.";
    public static final String CONTACT_EXISTS = "Existencia do contacto referido.";
    public static final String CONTACT_NOT_EXISTS = "Inexistencia do contacto referido.";
    public static final String CONTACT_NOT_REMOVED = "Contacto nao pode ser removido.";
    public static final String NO_CONTACTS = "Inexistencia de contactos.";
    public static final String GROUP_EXISTS = "Grupo ja existente.";
    public static final String GROUP_NOT_EXISTS = "Inexistencia do grupo referido.";
    public static final String SUBSCRIPTION_EXISTS = "Existencia de aderencia referida.";
    public static final String SUBSCRIPTION_NOT_EXISTS_RP = "Inexistencia de aderencia referida.";
    public static final String NO_PARTICIPANTS = "Inexistencia de participantes.";
    public static final String SUBSCRIPTION_NOT_EXISTS = "Inexistencia do participante referido.";
    public static final String NO_CONTACT_MESSAGES = "Contacto nao tem mensagens.";
    public static final String NO_GROUP_MESSAGES = "Grupo nao tem mensagens.";


    public static void main(String[] args) {
        String cmd;
        ContactNet net = new ContactNetClass();
        boolean exit = false;

        do {
            System.out.print(PROMPT);
            Scanner in = new Scanner(System.in);
            cmd = in.next();
            if (cmd.equalsIgnoreCase(INSERT_USER))
                insertUser(in, net);
            else if (cmd.equalsIgnoreCase(SHOW_USER))
                showUser(in, net);
            else if (cmd.equalsIgnoreCase(INSERT_CONTACT))
                insertContact(in, net);
            else if (cmd.equalsIgnoreCase(REMOVE_CONTACT))
                removeContact(in, net);
            else if (cmd.equalsIgnoreCase(LIST_CONTACTS))
                listContacts(in, net);
            else if (cmd.equalsIgnoreCase(INSERT_GROUP))
                insertGroup(in, net);
            else if (cmd.equalsIgnoreCase(SHOW_GROUP))
                showGroup(in, net);
            else if (cmd.equalsIgnoreCase(REMOVE_GROUP))
                removeGroup(in, net);
            else if (cmd.equalsIgnoreCase(INSERT_GROUP_PARTICIPANT))
                subscribeGroup(in, net);
            else if (cmd.equalsIgnoreCase(REMOVE_GROUP_PARTICIPANT))
                removeSubscription(in, net);
            else if (cmd.equalsIgnoreCase(LIST_GROUP_PARTICIPANTS))
                listParticipants(in, net);
            else if (cmd.equalsIgnoreCase(INSERT_MESSAGE))
                insertMessage(in, net);
            else if (cmd.equalsIgnoreCase(LIST_CONTACT_MESSAGES))
                listContactMessages(in, net);
            else if (cmd.equalsIgnoreCase(LIST_GROUP_MESSAGES))
                listGroupMessages(in, net);
            else if (cmd.equalsIgnoreCase(EXIT)) {
                in.nextLine(); in.nextLine();
                System.out.println(EXIT_MESSAGE);
                exit = true;
            }
            else {//comando desconhecido
                //usar esta linha so para debug
                //System.out.println("Comando desconhecido: "+cmd);
                in.nextLine(); in.nextLine();
            }
        } while (!exit);

    }

    /**
     *
     * Inserts an user with a provided login, name, age, address and profession into the contact network. It will fail
     * if the network already contains an user with same login as the one provided
     * @param in Scanner from where we're reading the inputs
     * @param net Contact network where it will be inserted the new user
     * @pre in != null && net != null
     */
    private static void insertUser(Scanner in, ContactNet net) {
        String login = in.next().toUpperCase();
        String name = in.nextLine().trim().toUpperCase();
        int age = in.nextInt();
        String address = in.nextLine().trim().toUpperCase();
        String profession = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            net.insertUser(login, name, age, address, profession);
            System.out.println(INSERT_USER_OK);
        } catch (UserExists e) {
            System.out.println(USER_EXISTS);
        }
    }

    /**
     * Displays an user with given login its info, mainly its login, name, age, address and their profession. It will fail
     * if the contact network does not contain an user with given login.
     * @param in Scanner from where we're reading the inputs
     * @param net Contact network where we will find the registered user with given login
     * @pre in != null && net != null
     */
    private static void showUser(Scanner in, ContactNet net) {
        String login = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            UserData user = net.showUser(login);
            System.out.println(user.getLogin() + " " + user.getName() + " "
                    + user.getAge());
            System.out.println(user.getAddress() + " " + user.getProfession());
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        }
    }

    /**
     * Creates a contact between two users registered in the contact network, identified by their respective logins,
     * and both of them can receive messages the other writes. It will fail if one of the users aren't registered in the
     * network or they are already contacts between each other.
     * @param in Scanner where we will read inputs from
     * @param net Contact network where the two users are registered
     * @pre in != null && net != null
     */
    private static void insertContact(Scanner in, ContactNet net) {
        String login1 = in.next().toUpperCase();
        String login2 = in.next().toUpperCase();
        in.nextLine(); in.nextLine();

        try {
            net.insertContact(login1, login2);
            System.out.println(INSERT_CONTACT_OK);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (ContactExists e) {
            System.out.println(CONTACT_EXISTS);
        }
    }

    /**
     * Removes a contact between two users, identified by their respective logins, and they will not be able to receive
     * messages from each other when one writes. It will fail when one of the users isn't registered in the network or
     * they already dont have a contact between them.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact network where the two users are registered
     * @pre in != null && net != null
     */
    private static void removeContact(Scanner in, ContactNet net) {
        String login1 = in.next().toUpperCase();
        String login2 = in.next().toUpperCase();
        in.nextLine(); in.nextLine();
        try {
            net.removeContact(login1, login2);
            System.out.println(REMOVE_CONTACT_OK);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (ContactNotExists e) {
            System.out.println(CONTACT_NOT_EXISTS);
        } catch (ContactNotRemoved e) {
            System.out.println(CONTACT_NOT_REMOVED);
        }
    }

    /**
     * Lists all contacts that an user with given login has in that time. It will fail if the user isn't registered in
     * the network or the user doesn't have any contacts to begin with.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact network where the user is registered
     * @pre in != null && net != null
     */
    private static void listContacts(Scanner in, ContactNet net) {
        String login = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            Iterator<User> it = net.listContacts(login);
            printUsers(it);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (NoContacts e) {
            System.out.println(NO_CONTACTS);
        }
    }


    /**
     * Lists all the users present within the given iterator, mainly their login and name
     * @param users Iterator that iterates throughout a collection of Users
     * @pre users != null
     */
    private static void printUsers(Iterator<User> users) {
        while (users.hasNext()) {
			UserData u = users.next();
            System.out.printf("%s %s\n", u.getLogin(), u.getName());
        }
    }

    /**
     * Creates an group within the network where users can join to write to each other in group, rather than in contacts.
     * It requires a name and description for the group. It fails if the network already has a group with same name as
     * the one provided
     * @param in Scanner where we will be reading inputs from
     * @param net Contact network where will be inserting the group
     * @pre in != null && net != null
     */
    private static void insertGroup(Scanner in, ContactNet net) {
        String name = in.nextLine().trim().toUpperCase();
        String text = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            net.insertGroup(name, text);
            System.out.println(INSERT_GROUP_OK);
        } catch (GroupExists e) {
            System.out.println(GROUP_EXISTS);
        }
    }

    /**
     * Displays a group with given name their name (lol?) and the description that it was given to the group. It fails if
     * the network does not contain a group with the same name as the one given.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact Network where we will get the group from
     * @pre in != null && net != null
     */
    private static void showGroup(Scanner in, ContactNet net) {
        String name = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            GroupData g = net.showGroup(name);
            System.out.println(g.getName());
            System.out.println(g.getDescription());
        } catch (GroupNotExists e) {
            System.out.println(GROUP_NOT_EXISTS);
        }
    }

    /**
     * Removes a group with given name from the contact network, making all its participants leave the group automatically.
     * It fails if the network does not have a group with the same name as the one given.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact network where we will remove the group
     * @pre in != null && net != null
     */
    private static void removeGroup(Scanner in, ContactNet net) {
        String name = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            net.removeGroup(name);
            System.out.println(REMOVE_GROUP_OK);
        } catch (GroupNotExists e) {
            System.out.println(GROUP_NOT_EXISTS);
        }

    }


    private static void subscribeGroup(Scanner in, ContactNet net) {
        String login = in.next().toUpperCase();
        String group = in.next().toUpperCase();
        in.nextLine(); in.nextLine();

        try {
            net.subscribeGroup(login, group);
            System.out.println(SUBSCRIBE_GROUP_OK);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (GroupNotExists e) {
            System.out.println(GROUP_NOT_EXISTS);
        } catch (SubscriptionExists e) {
            System.out.println(SUBSCRIPTION_EXISTS);
        }
    }

    /**
     * Removes an user with given login from a group with given name, making them unable to receive messages from the
     * group unless they join the group again. It fails if the network doesn't have an user with the same login or a
     * group with the same name as the one provided.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact Network where we will execute the actions described above
     * @pre in != null && net != null
     */
    private static void removeSubscription(Scanner in, ContactNet net) {
        String login = in.next().toUpperCase();
        String name = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            net.removeSubscription(login, name);
            System.out.println(REMOVE_SUBSCRIPTION_OK);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (GroupNotExists e) {
            System.out.println(GROUP_NOT_EXISTS);
        } catch (SubscriptionNotExists e) {
            System.out.println(SUBSCRIPTION_NOT_EXISTS_RP);
        }
    }

    /**
     * Lists all the users that joined a group with given name, mainly their login and name. It fails if the group doesn't
     * exist in the network or the group doesn't have any users in it.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact Network where we will execute the actions described above.
     * @pre in != null && net != null
     */
    private static void listParticipants(Scanner in, ContactNet net) {
        String group = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            Iterator<User> it = net.listParticipants(group);
            printUsers(it);
        } catch (GroupNotExists e) {
            System.out.println(GROUP_NOT_EXISTS);
        } catch (NoParticipants e) {
            System.out.println(NO_PARTICIPANTS);
        }
    }

    /**
     * Inserts a message, made by an user with given login, with the following title, text and its corresponding URL and
     * it's sent to all users who are a contact with the user and to the groups where the user belongs to. It fails when
     * the user that tries to send the message doesn't exist.
     * @param in Scanner where we will be reading inputs from
     * @param net Contact network where the message is gonna be sent
     * @pre in != null && net != null
     */
    private static void insertMessage(Scanner in, ContactNet net) {
        String login = in.nextLine().trim().toUpperCase();
        String title = in.nextLine().trim().toUpperCase();
        String text = in.nextLine().trim().toUpperCase();
        String url = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            net.insertMessage(login, title, text, url);
            System.out.println(INSERT_MESSAGE_OK);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        }
    }

    /**
     * Lists all the messages made by an user with given login, requested by an user who is a contact with previous user.
     * The user can request to see its own messages. It only fails when the user requesting access, the user with the messages
     * doesn't exist or when the list of messages from the user is empty or the users don't have a contact between each other.
     * @param in Scanner where we will be reading the inputs from
     * @param net Contact Network where we gather information required to list
     * @pre in != null && net != null
     */
    private static void listContactMessages(Scanner in, ContactNet net) {
        String login1 = in.next().toUpperCase();
        String login2 = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            Iterator<Message> it = net.listContactMessages(login1, login2);
            printMessages(it);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (ContactNotExists e) {
            System.out.println(CONTACT_NOT_EXISTS);
        } catch (NoContactMessages e) {
            System.out.println(NO_CONTACT_MESSAGES);
        }
    }

    /**
     * Lists all the messages made by all users who belong to a group with given name when requested by an user with given login and
     * belongs to the same group. It will fail when the user requesting access doesn't exist, when the group in question
     * isn't registered in the network.
     * @param in Scanner where we will be reading the inputs from
     * @param net Contact network where we gather information required to list
     * @pre in != null && net != null
     */
    private static void listGroupMessages(Scanner in, ContactNet net) {
        String name = in.next().toUpperCase();
        String login1 = in.nextLine().trim().toUpperCase();
        in.nextLine();
        try {
            Iterator<Message> it = net.listGroupMessages(name, login1);
            printMessages(it);
        } catch (GroupNotExists e) {
            System.out.println(GROUP_NOT_EXISTS);
        } catch (UserNotExists e) {
            System.out.println(USER_NOT_EXISTS);
        } catch (SubscriptionNotExists e) {
            System.out.println(SUBSCRIPTION_NOT_EXISTS);
        } catch (NoGroupMessages e) {
            System.out.println(NO_GROUP_MESSAGES);
        }
    }

    /**
     * Prints the messages within the format of "Title, Description, URL" into the console
     * @param messages Iterator that iterates through a collection of messages
     * @pre messages != null
     */
    private static void printMessages(Iterator<Message> messages) {
        while (messages.hasNext()) {
            Message m = messages.next();
            System.out.printf("%s\n%s\n%s\n", m.getTitle(), m.getDescription(), m.getURL());
            System.out.println();
        }

    }



}
