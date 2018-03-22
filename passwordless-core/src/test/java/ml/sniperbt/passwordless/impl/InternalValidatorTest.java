package ml.sniperbt.passwordless.impl;

import ml.sniperbt.passwordless.ExpiredTokenException;
import ml.sniperbt.passwordless.InvalidTokenException;
import ml.sniperbt.passwordless.Token;
import ml.sniperbt.passwordless.TokenValidator;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class InternalValidatorTest {


    private final TokenValidator sut = new InternalValidator();

    @Test
    public void testValidationSuccess() throws Exception {
        sut.validate(new Token("5C1D3C7FE08352E", OffsetDateTime.now().plusDays(15)));
    }

    @Test
    public void testValidationFailsExpired() {
        try {
            sut.validate(new Token("5C1D3C7FE08352E", OffsetDateTime.now().minusDays(15)));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ExpiredTokenException);
        }
    }

    @Test
    public void testValidationFailsInvalid() {
        try {
            sut.validate(new Token("test", OffsetDateTime.now().minusDays(15)));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof InvalidTokenException);
        }
    }

}