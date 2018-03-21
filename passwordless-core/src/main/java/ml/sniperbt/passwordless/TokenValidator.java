package ml.sniperbt.passwordless;

public interface TokenValidator {
    String validate(final String token) throws InvalidTokenException;
}