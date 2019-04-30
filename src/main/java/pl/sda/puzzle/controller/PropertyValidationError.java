package pl.sda.puzzle.controller;

import pl.sda.puzzle.tables.User;

public class PropertyValidationError extends User {
    private String property;
    private String message;

    public PropertyValidationError(String property, String message) {
        this.property = property;
        this.message = message;
    }

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }

    public static void main(String[] args) {
        new PropertyValidationError("title", "must not be null");
    }
}
