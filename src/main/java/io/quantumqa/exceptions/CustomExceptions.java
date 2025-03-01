package io.quantumqa.exceptions;

/*
USAGE
throw new CustomExceptions.ElementNotFoundException("Element not found: " + locator);
 */
public class CustomExceptions {

    public static class FrameworkException extends RuntimeException {
        public FrameworkException(String message) {
            super(message);
        }

        public FrameworkException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class ElementNotFoundException extends FrameworkException {
        public ElementNotFoundException(String message) {
            super(message);
        }
    }

    public static class TestDataException extends FrameworkException {
        public TestDataException(String message) {
            super(message);
        }
    }
}
