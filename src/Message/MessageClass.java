package Message;

public class MessageClass implements Message{

    private String title;
    private String description;
    private String url;

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
