package ru.tinkoff.edu.java.linkParser.validators;

public abstract class Validator {
    public Validator next;

    public abstract Boolean validate();

    protected boolean validateNext() {
        if (next == null) {
            return true;
        }
        return next.validate();
    }
}
