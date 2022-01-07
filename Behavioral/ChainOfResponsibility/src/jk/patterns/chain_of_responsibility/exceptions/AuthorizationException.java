package jk.patterns.chain_of_responsibility.exceptions;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String name) {
        super(String.format("Person '%s' is not a registered customer.", name));
    }
}
