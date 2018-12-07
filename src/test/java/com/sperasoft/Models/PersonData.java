package com.sperasoft.Models;

public class PersonData {

    public enum Genre {
        MALE,
        FEMALE
    }

    private Genre genre;
    private String firstname;
    private String lastname;
    private String email;
    private String passwd;
    private String address;
    private String city;
    private String state;
    private String postcode;
    private String mobilephone;
    private String alias;

    public PersonData(Genre genre, String firstname, String lastname, String email, String passwd, String address,
                      String city, String state, String postcode, String mobilephone, String alias) {
        setGenre(genre);
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPasswd(passwd);
        setAddress(address);
        setCity(city);
        setState(state);
        setPostcode(postcode);
        setMobilephone(mobilephone);
        setAlias(alias);
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
