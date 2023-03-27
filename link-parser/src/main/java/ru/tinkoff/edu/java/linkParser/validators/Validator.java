package ru.tinkoff.edu.java.linkParser.validators;

public abstract class Validator {
    private Validator next;

    public static Validator link(Validator first, Validator... chain) {
        Validator head = first;
        for (Validator nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Boolean validate();

    protected boolean validateNext() {
        if (next == null) {
            return true;
        }
        return next.validate();
    }
}
