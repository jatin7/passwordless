package ml.sniperbt.passwordless.impl;

import ml.sniperbt.passwordless.TokenValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class InternalValidator implements TokenValidator {
    private final Pattern REGEX = Pattern.compile("");
    @Override
    public boolean validate(String token) {
        final Matcher matcher = REGEX.matcher(token);
        return matcher.matches();
    }
}
