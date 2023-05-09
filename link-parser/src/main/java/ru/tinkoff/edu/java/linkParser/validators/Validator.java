package ru.tinkoff.edu.java.linkParser.validators;

public abstract class Validator {
    private Validator next;

    public Validator getNext() {
        return next;
    }

    public void setNext(Validator next) {
        this.next = next;
    }

    public abstract Boolean validate();

    protected boolean validateNext() {
        if (next == null) {
            return true;
        }
        return next.validate();
    }
}
