package ml.sniperbt.passwordless;

public interface TokenValidator {
    Token validate(final Token token) throws InvalidTokenException, ExpiredTokenException;
}