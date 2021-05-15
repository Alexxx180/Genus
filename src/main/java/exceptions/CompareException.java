package exceptions;

import java.io.IOException;

public class CompareException extends IOException {
    public CompareException() {
<<<<<<< HEAD
=======

>>>>>>> level5
    }

    public CompareException(String message) {
        super(message);
    }

    public CompareException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompareException(Throwable cause) {
        super(cause);
    }
}
