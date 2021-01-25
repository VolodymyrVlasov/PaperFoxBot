package models.users;

public class Customer {
    private long chatID;
    private String firstName;
    private String familyName;
    private String phoneNumber;
    private String email;



    public long getChatID() {
        return chatID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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
        return "Customer -> " +
                "name: " + firstName +
                ", familyName: " + familyName +
                ", phoneNumber='" + phoneNumber +
                ", email='" + email;
    }
}
