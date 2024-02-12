package com.CheckYourUnderstanding.Task2.util.exception;

public class UserNotFoundException extends RuntimeException{
    public  UserNotFoundException(String str){
        super(str);
    }
}
