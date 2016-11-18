package logic;

import java.util.TimeZone;

/**
 * Created by shai on 10/3/16.
 */
public class PersonalInfo {

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }
    public enum Language {
        ENGLISH
    }
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private Language lang;
    private String address;
    private String/*change*/ photo;
    private String description;
    private TimeZone timeZone;

    public PersonalInfo(String firstName, String lastName, Gender gender, String email, Language lang, String address, String photo, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.lang = lang;
        this.address = address;
        this.photo = photo;
        this.description = description;
    }
    public PersonalInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
}
