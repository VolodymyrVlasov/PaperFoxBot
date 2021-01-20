package models.users;

import models.users.conditions.UserStates;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.GregorianCalendar;

public class ChatUser {
    private final long chatID;
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

    public void setPassportFields(Update update) {
        if (update.hasMessage()) {
            this.firstName = update.getMessage().getChat().getFirstName();
            this.familyName = update.getMessage().getChat().getLastName();
        }
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

    public long getChatID() {
        return chatID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public GregorianCalendar getLastAction() {
        return lastAction;
    }

    public void setLastAction(GregorianCalendar lastAction) {
        this.lastAction = lastAction;
    }
}


