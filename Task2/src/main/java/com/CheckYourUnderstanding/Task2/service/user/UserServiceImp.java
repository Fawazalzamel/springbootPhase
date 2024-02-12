package com.CheckYourUnderstanding.Task2.service.user;

import com.CheckYourUnderstanding.Task2.bo.user.CreateUserRequest;
import com.CheckYourUnderstanding.Task2.bo.user.UpdateUserStatusRequest;
import com.CheckYourUnderstanding.Task2.entity.UserEntity;
import com.CheckYourUnderstanding.Task2.ropsitory.UserRepositry;
import com.CheckYourUnderstanding.Task2.util.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepositry userRepositry;

    public UserServiceImp(UserRepositry userRepositry){

        this.userRepositry=userRepositry;

    }
    @Override
    public void saveUser(CreateUserRequest createUserRequest){
        UserEntity userEntity= new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepositry.save(userEntity);
    }

    @Override
    public void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest){
        UserEntity userEntity=userRepositry.findById(userId)
                .orElseThrow();
        if(!updateUserStatusRequest.getStatus().equals("ACTIVE") && !updateUserStatusRequest.getStatus().equals("INACTIVE")) {
            throw new IllegalArgumentException("The status should be ACTIVE or INACTIVE");
        }
        userEntity.setStatus(Status.valueOf(updateUserStatusRequest.getStatus()));
        userRepositry.save(userEntity);

    }

}

