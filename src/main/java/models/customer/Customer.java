package models.customer;

import java.util.GregorianCalendar;

public class Customer {
//    private long chatID;

    private  String uuid;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public GregorianCalendar createdAt;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return firstName +" " + lastName +
                "\ttel: " + phoneNumber +
                "\te-mail: " + email;
    }
}
