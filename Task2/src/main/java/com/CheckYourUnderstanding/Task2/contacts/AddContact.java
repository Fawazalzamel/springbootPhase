package com.CheckYourUnderstanding.Task2.contacts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddContact {


    List<CreateContactRequest> allContacts = new ArrayList<>();
    @PostMapping("/addContact")
    public ResponseEntity<String> addContact(@RequestBody CreateContactRequest createContactRequest) {
        for (int i=0;i<allContacts.size();i++){
            if (allContacts.get(i).getEmail().equals(createContactRequest.getEmail())){
                return  ResponseEntity.badRequest().body("Contact Already existed");
            }
        }
        System.out.println(allContacts);
        allContacts.add(createContactRequest);
        return  ResponseEntity.ok("Contact added successfully");
    }
    @GetMapping("/getContactDetails")
    public ResponseEntity<?> getContactDetails(@RequestParam String name){
        for (int i=0;i<allContacts.size();i++){
            System.out.println(allContacts.get(i).toString());
            if (allContacts.get(i).getName().equals(name)){

                return ResponseEntity.ok(allContacts.get(i));
            }
        }
        return ResponseEntity.badRequest().body("Contact not founded");
    }

}