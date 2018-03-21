package ml.sniperbt.passwordless;

public interface TokenStore {

    Token createToken(final String key);

    void validateToken(final Token token) throws InvalidTokenException;
}
