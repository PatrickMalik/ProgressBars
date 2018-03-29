package at.disasterapps.progressbars.exceptions;

public class CurrentIsSmallerThanZeroException extends Exception {
    public CurrentIsSmallerThanZeroException(String message){
        super(message);
    }
}
