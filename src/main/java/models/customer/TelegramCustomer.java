package models.customer;

import models.shop.ShoppingCart;
import models.customer.conditions.UserStates;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.GregorianCalendar;

public class TelegramCustomer extends Customer {
    private final long chatID;
//    private String firstName;
//    private String familyName;
//    private String phoneNumber;
//    private String email;
    private UserStates state;
    private ShoppingCart shoppingCart;
    private boolean keyboardSend = false;



    public TelegramCustomer(long chatID) {
        this.chatID = chatID;
        this.state = UserStates.USER_CONNECTED;
        this.createdAt = new GregorianCalendar();
    }

    public void setPassportFields(Update update) {
        if (update.hasMessage()) {
            this.firstName = update.getMessage().getChat().getFirstName();
            this.lastName = update.getMessage().getChat().getLastName();
            System.out.println("31(TU) new Telegram User: " + this.firstName + " " + this.lastName);
        }
    }

    public Customer getCustomer() {
        Customer customer = new Customer();
        if (email != null) customer.setEmail(email);
        if (phoneNumber != null) customer.setPhoneNumber(phoneNumber);
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        return customer;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void clearShoppingCart() {
        this.shoppingCart = null;
    }

    public void setShoppingCart(ShoppingCart orderCart) {
        this.shoppingCart = orderCart;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setState(UserStates   state) {
        this.state = state;
        this.createdAt = new GregorianCalendar();
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

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public GregorianCalendar getLastAction() {
        return createdAt;
    }

    public void setLastAction(GregorianCalendar lastAction) {
        this.createdAt = lastAction;
    }

    public boolean isKeyboardSend() {
        return keyboardSend;
    }

    public void setKeyboardSend(boolean keyboardSend) {
        this.keyboardSend = keyboardSend;
    }

    @Override
    public String toString() {
        return "ChatUser{\n" +
                "   chatID=" + chatID + "'\n" +
                "   firstName='" + firstName + "'\n" +
                "   familyName='" + lastName + "'\n" +
                "   phoneNumber='" + phoneNumber + "'\n" +
                "   email='" + email + "'\n" +
                "   state=" + state + "'\n" +
                "   orderCart=" + shoppingCart + "'\n" +
                "}";
    }
}


