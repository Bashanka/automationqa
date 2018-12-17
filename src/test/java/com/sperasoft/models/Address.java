package com.sperasoft.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "address1", "address2", "city", "state", "postcode", "homephone", "addInfo", "title"})

public class Address {

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postcode;
    private String homephone;
    private String addInfo;
    private String title;

}
