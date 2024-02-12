package com.CheckYourUnderstanding.Task2.controller.userController;

import com.CheckYourUnderstanding.Task2.bo.user.UpdateUserStatusRequest;
import com.CheckYourUnderstanding.Task2.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CheckYourUnderstanding.Task2.bo.user.CreateUserRequest;


@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/sayHi")
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("Hello");
    }


    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
        userService.saveUser(createUserRequest);
    }catch(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok("User Created Successfully");
}

@PutMapping("/update-user-status")
    public ResponseEntity<String>updateUser(@RequestParam Long userId, @RequestParam UpdateUserStatusRequest UpdateUserStatusRequest) {
    try {
        userService.updateUserStatus(userId, UpdateUserStatusRequest);

    } catch (IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
return ResponseEntity.ok("User update successfully");
}
}
