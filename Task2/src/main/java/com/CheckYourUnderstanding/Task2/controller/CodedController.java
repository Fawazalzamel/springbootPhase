package com.CheckYourUnderstanding.Task2.controller;



import com.CheckYourUnderstanding.Task2.bo.CreateContactRequest;
import com.CheckYourUnderstanding.Task2.bo.CreateFarewellRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class CodedController {

    List<CreateContactRequest> allContact = new ArrayList<>();
    @GetMapping("/sayHi")
    public String sayHi(){
        return "Welcome to Coded";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name){
        return "Hello, "+ name;
    }

    @PostMapping("/farewell")
    public String farewell(@RequestBody CreateFarewellRequest createFarewellRequest){
        return "GoodBye, "+ createFarewellRequest.getName();
    }

    @PostMapping("/addContact")
    public ResponseEntity<String> AddContact(@RequestBody  CreateContactRequest createContactRequest){
        for(int i=0;i<allContact.size();i++){
            System.out.println(allContact.get(i).getName());
            if(allContact.get(i).getEmail().equals(createContactRequest.getEmail())){
                return ResponseEntity.badRequest().body("Contact already exist with this email");
            }
        }
        allContact.add(createContactRequest);
        return ResponseEntity.ok("Contact added successfully!");
    }

    @GetMapping("/getContactDetails")
    public ResponseEntity<?> getContactDetails(@RequestParam String name){
        for(int i=0; i<allContact.size(); i++){
            System.out.println(allContact.get(i).toString());
            if (allContact.get(i).getName().equals(name)){

                return ResponseEntity.ok(allContact.get(i));
            }
        }
        return  ResponseEntity.badRequest().body("Contact not found");
    }
}
