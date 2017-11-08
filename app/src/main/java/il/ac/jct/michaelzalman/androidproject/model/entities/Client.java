package il.ac.jct.michaelzalman.androidproject.model.entities;

import android.provider.ContactsContract;

/**
 * Created by מיכאל on 08/11/2017.
 */

public class Client
{
    String firstName;
    String lastName;
    String id;
    String phoneNumber;
    String email;
    String creditCard;



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
