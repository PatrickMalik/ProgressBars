package at.disasterapps.progressbars.exceptions;

public class CurrentIsBiggerThanEndException extends Exception {

    public CurrentIsBiggerThanEndException(String message){
        super(message);
    }
}
