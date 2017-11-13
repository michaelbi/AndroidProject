package il.ac.jct.michaelzalman.androidproject.model.entities;

/**
 * Created by מיכאל on 13/11/2017.
 */

public class Address {

        String city;
        String street;
        int number;

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        public int getNumber() {
            return number;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setNumber(int number) {
            this.number = number;
        }

}
