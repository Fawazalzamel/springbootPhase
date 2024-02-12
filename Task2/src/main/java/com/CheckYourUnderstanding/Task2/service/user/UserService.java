package com.CheckYourUnderstanding.Task2.service.user;

import com.CheckYourUnderstanding.Task2.bo.user.CreateUserRequest;
import com.CheckYourUnderstanding.Task2.bo.user.UpdateUserStatusRequest;

public interface UserService {

    void saveUser(CreateUserRequest createUserRequest);

    void updateUserStatus(Long userID, UpdateUserStatusRequest updateUserStatusRequest);
}
