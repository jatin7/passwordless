package ml.sniperbt.passwordless.impl;

import ml.sniperbt.passwordless.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTokenStore implements TokenStore {

    private final Map<String, Token> storage;
    private final Configuration config;
    private final TokenValidator validator;

    public InMemoryTokenStore() {
        this.validator = new InternalValidator();
        this.config = new Configuration() {
        };
        this.storage = new ConcurrentHashMap<>(0);
    }

    private InMemoryTokenStore(Builder builder) {
        this.storage = builder.storage;
        this.config = builder.config;
        this.validator = builder.validator;
    }


    @Override
    public Token createToken(String key) {
        SecureRandom random = new SecureRandom();

        final BigInteger tokenInt = new BigInteger(config.getTokenByteSize(), random);
        final TTL ttl = config.getTTL();
        final OffsetDateTime expirationDate = OffsetDateTime.now().plus(ttl.getValue(), ttl.getTimeUnit());
        final Token token = new Token(tokenInt.toString(16).toUpperCase(), expirationDate);
        storage.put(key, token);
        return token;
    }

    @Override
    public Token validateToken(Token token) throws InvalidTokenException, ExpiredTokenException {
        return validator.validate(token);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Map<String, Token> storage = new ConcurrentHashMap<>();
        private TokenValidator validator = new InternalValidator();
        private Configuration config = new Configuration() {
        };

        private Builder() {
        }

        public Builder storage(Map<String, Token> storage) {
            this.storage = storage;
            return this;
        }

        public Builder configuration(Configuration config) {
            this.config = config;
            return this;
        }

        public Builder validator(TokenValidator validator) {
            this.validator = validator;
            return this;
        }

        public InMemoryTokenStore build() {
            return new InMemoryTokenStore(this);
        }
    }
}
