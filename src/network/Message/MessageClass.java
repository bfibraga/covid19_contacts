package network.Message;

/**
 * An class that contains all the methods to get the message information.
 * @author 57747_57833
 */
public class MessageClass implements Message{

    /**
     * Title of the message as <code>String</code>
     */
    private String title;

    /**
     * Description of the message as <code>String</code>
     */
    private String description;

    /**
     * URL of the message as <code>String</code>
     */
    private String url;

    /**
     * Creates a message with a given title, description and url
     * @param title Title of the message
     * @param description Description of the message
     * @param url URL of the message
     * @pre title != null && description != null && url != null
     */
    public MessageClass(String title, String description, String url){
        this.title = title;
        this.description = description;
        this.url = url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getURL() {
        return url;
    }
}
