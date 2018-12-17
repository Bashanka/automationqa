package com.sperasoft.models;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "gender", "firstname", "lastname", "email", "passwd", "dateOfBirth", "addresses", "mobilephone"})

public class Account {

    public enum Gender {
        MALE,
        FEMALE
    }

    private Gender gender;
    private String firstname;
    private String lastname;
    private String email;
    private String passwd;
    private Date dateOfBirth;
    private ArrayList<Address> addresses;
    private String mobilephone;

//    public Account(Gender gender, String firstname, String lastname, String email, String passwd, BDay bDay,
//                      String address, String address2, String city, String state, String additionalInformation,
//                      String postcode, String homephone, String mobilephone, String alias) {
//        setGender(gender);
//        setFirstname(firstname);
//        setLastname(lastname);
//        setEmail(email);
//        setPasswd(passwd);
//        setbDay(bDay);
//        setAddress(address);
//        setAddress2(address2);
//        setCity(city);
//        setState(state);
//        setAdditionalInformation(additionalInformation);
//        setPostcode(postcode);
//        setHomephone(homephone);
//        setMobilephone(mobilephone);
//        setAlias(alias);
//    }

//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPasswd() {
//        return passwd;
//    }
//
//    public BDay getbDay() {
//        return bDay;
//    }
//
//    public void setbDay(BDay bDay) {
//        this.bDay = bDay;
//    }
//
//    public void setPasswd(String passwd) {
//        this.passwd = passwd;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String[] getAddresses() {
//        return addresses;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public String getAdditionalInformation() {
//        return additionalInformation;
//    }
//
//    public void setAdditionalInformation(String additionalInformation) {
//        this.additionalInformation = additionalInformation;
//    }

//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getPostcode() {
//        return postcode;
//    }
//
//    public void setPostcode(String postcode) {
//        this.postcode = postcode;
//    }
//
//    public String getHomephone() {
//        return homephone;
//    }
//
//    public void setHomephone(String homephone) {
//        this.homephone = homephone;
//    }
//
//    public String getMobilephone() {
//        return mobilephone;
//    }
//
//    public void setMobilephone(String mobilephone) {
//        this.mobilephone = mobilephone;
//    }
//
//    public String getAlias() {
//        return alias;
//    }
//
//    public void setAlias(String alias) {
//        this.alias = alias;
//    }
}
