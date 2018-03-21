package ml.sniperbt.passwordless;

import java.io.Serializable;

public class Token implements Serializable {

    private final String token;
    private final Long expirationDate;

    public Token(final String token, final Long expiratonDate) {
        this.token = token;
        this.expirationDate = expiratonDate;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return token;
    }
}
