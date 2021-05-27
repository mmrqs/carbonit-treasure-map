package fr.carbonit.parser.exception;

import lombok.NonNull;

public class NumberOfArgumentException extends ParserException {
    private static final String ERROR_MESSAGE = "Expected %d argument(s), gotten %d argument(s).";

    public NumberOfArgumentException(
            @NonNull int expectedNumber,
            @NonNull int numberEntered) {
        super(String.format(ERROR_MESSAGE, expectedNumber, numberEntered));
    }
}
