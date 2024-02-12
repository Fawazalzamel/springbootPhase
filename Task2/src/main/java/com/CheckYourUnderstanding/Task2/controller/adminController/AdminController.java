package com.CheckYourUnderstanding.Task2.controller.adminController;

import com.CheckYourUnderstanding.Task2.entity.UserEntity;
import com.CheckYourUnderstanding.Task2.ropsitory.UserRepositry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserRepositry userRepositry;

    public AdminController(UserRepositry userRepositry){
        this.userRepositry=userRepositry;



    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUsers(){
        List<UserEntity> users = userRepositry.findAll();
        return ResponseEntity.ok().body(users);
    }
}
