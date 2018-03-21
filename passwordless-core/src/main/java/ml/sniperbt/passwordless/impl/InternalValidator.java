package ml.sniperbt.passwordless.impl;

import ml.sniperbt.passwordless.ExpiredTokenException;
import ml.sniperbt.passwordless.InvalidTokenException;
import ml.sniperbt.passwordless.Token;
import ml.sniperbt.passwordless.TokenValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class InternalValidator implements TokenValidator {
    private final Pattern REGEX = Pattern.compile("");

    public Token validate(Token token) throws InvalidTokenException, ExpiredTokenException {
        final Matcher matcher = REGEX.matcher(token.toString());
        if (matcher.matches()) {
            if (token.getExpirationDate() - System.currentTimeMillis() > 0) {
                return token;
            } else {
                throw new ExpiredTokenException("Expired");
            }
        } else {
            throw new InvalidTokenException("Token doesn't match regex " + REGEX.pattern());
        }
    }

}
