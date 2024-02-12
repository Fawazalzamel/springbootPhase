package com.CheckYourUnderstanding.Task2.service.auth;

import com.CheckYourUnderstanding.Task2.bo.auth.AuthenticationResponse;
import com.CheckYourUnderstanding.Task2.bo.auth.CreateLoginRequest;
import com.CheckYourUnderstanding.Task2.bo.auth.CreateSignupRequest;
import com.CheckYourUnderstanding.Task2.bo.auth.LogoutResponse;

public interface AuthService {

    void signup(CreateSignupRequest createSignupRequest);

    AuthenticationResponse login(CreateLoginRequest createLoginRequest);

    void logout(LogoutResponse logoutResponse);
}
