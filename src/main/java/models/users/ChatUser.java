package models.users;

import java.util.GregorianCalendar;

public class ChatUser {
    private long chatID;
    private String firstName;
    private String familyName;
    private String phoneNumber;
    private String email;
    private UserStates state;
    private GregorianCalendar lastAction;

    public ChatUser(long chatID) {
        this.chatID = chatID;
        this.state = UserStates.USER_CONNECTED;
        this.lastAction = new GregorianCalendar();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(UserStates state) {
        this.state = state;
        this.lastAction = new GregorianCalendar();
    }

    public UserStates getState() {
        return state;
    }
}


