package edu.miu.ticket_system.exception;

public class UserNotFoundException  extends Exception{

    private String message;
    public UserNotFoundException(String message) {
        super(message);
    }
}
