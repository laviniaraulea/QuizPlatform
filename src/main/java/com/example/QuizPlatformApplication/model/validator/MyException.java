package com.example.QuizPlatformApplication.model.validator;

/**
 *  Custom exception class for handling application-specific exceptions.
 *  This exception can be used to indicate and handle errors in the Quiz Platform Application.
 */
public class MyException extends Exception {
    /**
     * Constructs a new MyException with no specified detail message.
     */
    public MyException() {
        super();
    }

    /**
     * Constructs a new MyException with the specified detail message.
     * @param message The detail message that provides information about the exception.
     */
    public MyException(String message) {
        super(message);
    }

    /**
     * Constructs a new MyException with the specified detail message and cause.
     * @param message The detail message that provides information about the exception.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new MyException with the specified cause.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public MyException(Throwable cause) {
        super(cause);
    }
}
