package ru.tinkoff.edu.java.linkParser.validators;

public class ValidatorChainBuilder {

    private Validator head;

    public ValidatorChainBuilder(Validator first, Validator... chain) {
        Validator head = first;
        for (Validator nextInChain : chain) {
            head.setNext(nextInChain);
            head = nextInChain;
        }
        this.head = first;
    }

    public Validator toValidator() {
        return head;
    }
}
