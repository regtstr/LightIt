package com.lightit.automated.qa.test.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserInfo {

    private Integer id;

    private String name;

    private String pass;

    private String email;
}
