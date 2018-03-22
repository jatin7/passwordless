package ml.sniperbt.passwordless;

public interface TokenStore {

    Token createToken(final String key);

    Token validateToken(final Token token) throws InvalidTokenException, ExpiredTokenException;
}
