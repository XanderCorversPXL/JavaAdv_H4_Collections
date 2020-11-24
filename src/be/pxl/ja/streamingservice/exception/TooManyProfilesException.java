package be.pxl.ja.streamingservice.exception;

public class TooManyProfilesException extends RuntimeException {
    public TooManyProfilesException(String message) {
        super(message);
    }
}
