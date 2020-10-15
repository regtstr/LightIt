package com.lightit.automated.qa.test.api.steps.postUserProfile;

import com.lightit.automated.qa.test.api.conditions.Conditions;
import com.lightit.automated.qa.test.api.models.UserInfo;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileModel;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileResponseModel;
import com.lightit.automated.qa.test.api.servise.createUserService.CreateUserService;
import io.qameta.allure.Step;

public class PostUserProfileStep {

    CreateUserService createUserService = new CreateUserService();

    @Step("Create new user return full info")
    public UserInfo postUserProfile() {

        UserInfo userInfo = new UserInfo();

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize();

        PostUserProfileResponseModel postUserProfileResponseModel = createUserService
                .createUser(postUserProfileModel)
                .shouldHave(Conditions.statusCode(201))
                .responseAs(PostUserProfileResponseModel.class);

        userInfo.setId(postUserProfileResponseModel.getUser().getPk());
        userInfo.setName(postUserProfileModel.getUsername());
        userInfo.setPass(postUserProfileModel.getPassword1());
        userInfo.setEmail(postUserProfileModel.getEmail());

        return userInfo;
    }
}
