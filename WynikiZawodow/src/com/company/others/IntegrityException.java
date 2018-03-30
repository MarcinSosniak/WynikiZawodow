package com.company.others;

public class IntegrityException extends Exception{
    private String _message;
    public IntegrityException() {super();}
    public IntegrityException(String message) {super(message);_message=message;}
    public void show() {System.out.println(_message);}
}
