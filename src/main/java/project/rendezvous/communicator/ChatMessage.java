package project.rendezvous.communicator;

public class ChatMessage  {

    private String name;
    private String username;
    private String text;
    private String time;

    public ChatMessage() {}

    public ChatMessage(String name, String username, String text, String time) {
        this.name = name;
        this.text = text;
        this.username = username;
        this.time = time;
    }

    public ChatMessage(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}