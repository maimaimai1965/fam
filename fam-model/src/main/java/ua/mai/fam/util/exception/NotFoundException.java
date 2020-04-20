package ua.mai.fam.util.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String entityName, String idName, String id) {
        super("Not exists " + entityName + " with " + idName + " = " + id + ".");
    }
}