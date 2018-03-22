package ml.sniperbt.passwordless.impl;

import ml.sniperbt.passwordless.ExpiredTokenException;
import ml.sniperbt.passwordless.InvalidTokenException;
import ml.sniperbt.passwordless.Token;
import ml.sniperbt.passwordless.TokenValidator;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InternalValidator implements TokenValidator {
    private final Pattern REGEX = Pattern.compile("[0-9a-fA-F]{15}");
    private final ZoneId timeZone;

    public InternalValidator() {
        timeZone = ZoneId.systemDefault();
    }

    public InternalValidator(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

    public Token validate(Token token) throws InvalidTokenException, ExpiredTokenException {
        final Matcher matcher = REGEX.matcher(token.toString());
        if (matcher.matches()) {
            if (token.getExpirationDate().isAfter(OffsetDateTime.now(timeZone))) {
                return token;
            } else {
                throw new ExpiredTokenException("Expired");
            }
        } else {
            throw new InvalidTokenException("Token doesn't match regex " + REGEX.pattern());
        }
    }

}
