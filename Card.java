package igor.contactCards;

public class Card {
    private String phoneNumber;
    private String name;
    private String content;

    public Card(String phoneNumber, String name, String content) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.content = content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
