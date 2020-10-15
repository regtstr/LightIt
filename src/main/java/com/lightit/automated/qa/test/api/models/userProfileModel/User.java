package com.lightit.automated.qa.test.api.models.userProfileModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User{

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("pk")
    private Integer pk;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;
}