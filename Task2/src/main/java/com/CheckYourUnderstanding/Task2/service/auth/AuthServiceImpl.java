package com.CheckYourUnderstanding.Task2.service.auth;

import com.CheckYourUnderstanding.Task2.bo.auth.AuthenticationResponse;
import com.CheckYourUnderstanding.Task2.bo.auth.CreateLoginRequest;
import com.CheckYourUnderstanding.Task2.bo.auth.CreateSignupRequest;
import com.CheckYourUnderstanding.Task2.bo.auth.LogoutResponse;
import com.CheckYourUnderstanding.Task2.bo.customUserDetails.CustomUserDetails;
import com.CheckYourUnderstanding.Task2.config.JWTutil;
import com.CheckYourUnderstanding.Task2.entity.RoleEntity;
import com.CheckYourUnderstanding.Task2.entity.UserEntity;

import com.CheckYourUnderstanding.Task2.ropsitory.RoleRepository;
import com.CheckYourUnderstanding.Task2.ropsitory.UserRepositry;
import com.CheckYourUnderstanding.Task2.util.enums.Roles;
import com.CheckYourUnderstanding.Task2.util.enums.Status;
import com.CheckYourUnderstanding.Task2.util.exception.BodyGuardException;
import com.CheckYourUnderstanding.Task2.util.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JWTutil jwtUtil;

    private final RoleRepository roleRepository;

    private final UserRepositry userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           CustomUserDetailsService userDetailsService,
                           JWTutil jwtUtil,
                           RoleRepository roleRepository,
                           UserRepositry userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity= new RoleEntity();
        roleEntity.setTitle(Roles.USER);
        roleRepository.save(roleEntity);
        UserEntity user= new UserEntity();
        user.setName(createSignupRequest.getName());
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setRoles(roleRepository.findByTitle(Roles.USER));
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");

        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();

        authentication(username, password);

        CustomUserDetails userDetails= userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer "+accessToken);
        return response;
    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    private void requiredNonNull(Object obj, String name){
        if(obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw  new UserNotFoundException("Incorrect username");
        }
    }
}
