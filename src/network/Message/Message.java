package network.Message;

public interface Message {

    /**
     * Returns the title of the message
     * @return Title of the message
     */
    String getTitle();

    /**
     * Returns the description of the message
     * @return Description of the message
     */
    String getDescription();

    /**
     * Returns the URL of the message
     * @return URL of the message
     */
    String getURL();
}
