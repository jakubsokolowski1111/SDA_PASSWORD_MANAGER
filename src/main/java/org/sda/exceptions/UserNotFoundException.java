package org.sda.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super();
        System.out.println("User not found.");
    }
}
