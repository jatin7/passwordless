package ml.sniperbt.passwordless.impl;

import ml.sniperbt.passwordless.InvalidTokenException;
import ml.sniperbt.passwordless.Token;
import ml.sniperbt.passwordless.TokenStore;
import ml.sniperbt.passwordless.TokenValidator;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTokenStore implements TokenStore {

    private final Map<String, Token> storage;
    private final int TOKEN_BYTE_SIZE;
    private final TokenValidator validator;

    public InMemoryTokenStore() {
        this.validator = new InternalValidator();
        this.TOKEN_BYTE_SIZE = 64;
        this.storage = new ConcurrentHashMap<>(0);
    }

    public InMemoryTokenStore(TokenValidator validator, int tokenByteSize) {
        this.TOKEN_BYTE_SIZE = tokenByteSize;
        this.validator = validator;
        this.storage = new ConcurrentHashMap<>(0);
    }

    public InMemoryTokenStore(Map<String, Token> storage, TokenValidator validator) {
        this.storage = storage;
        this.validator = validator;
        this.TOKEN_BYTE_SIZE = 64;
    }

    @Override
    public Token createToken(String key) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[TOKEN_BYTE_SIZE];
        random.nextBytes(bytes);
        final Base64.Encoder encoder = Base64.getEncoder();
        final String tokenString = encoder.encodeToString(bytes);
        final Token token = new Token(tokenString);
        storage.put(key, token);
        return token;
    }

    @Override
    public void validateToken(Token token) throws InvalidTokenException {
        validator.validate(token.toString());
    }
}
