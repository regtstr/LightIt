package com.lightit.automated.qa.test.api.models.userProfileModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import static com.lightit.automated.qa.test.api.utilits.dataGenerator.UserDataGeneratorV3.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostUserProfileModel {

    @JsonProperty("password2")
    private String password2;

    @JsonProperty("password1")
    private String password1;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    public PostUserProfileModel randomize(){
        this.username = getFakerUserName();
        this.email = getFakerEmailAddress();
        this.password1 = getFakerValidPasswordWithProperties(8, 10, true, true);
        this.password2 = password1;

        return this;
    }
}