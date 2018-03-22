package ml.sniperbt.passwordless.impl;


import ml.sniperbt.passwordless.ExpiredTokenException;
import ml.sniperbt.passwordless.InvalidTokenException;
import ml.sniperbt.passwordless.Token;
import ml.sniperbt.passwordless.TokenStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryTokenStoreTest {

    @Mock
    private InternalValidator validator;

    private Map<String, Token> storage = new ConcurrentHashMap<>();

    private TokenStore sut;

    private final Supplier<Token> tokenSupplier = () -> new Token("token", OffsetDateTime.now());


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = InMemoryTokenStore.builder()
                .validator(validator)
                .storage(storage)
                .build();
    }

    @Test
    public void testCreation() {
        final Token testToken = sut.createToken("test");
        final Token retrieved = storage.get("test");

        assertEquals(testToken, retrieved);
    }

    @Test
    public void testValidationSuccess() throws Exception {
        when(validator.validate(any(Token.class))).thenAnswer(i -> i.getArguments()[0]);
        sut.validateToken(tokenSupplier.get());
    }

    @Test
    public void testValidationFailsExpired() {
        try {
            when(validator.validate(any(Token.class))).thenThrow(new ExpiredTokenException(""));
            sut.validateToken(tokenSupplier.get());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ExpiredTokenException);
        }
    }

    @Test
    public void testValidationFailsInvalid() {
        try {
            when(validator.validate(any(Token.class))).thenThrow(new InvalidTokenException(""));
            sut.validateToken(tokenSupplier.get());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidTokenException);
        }
    }
}