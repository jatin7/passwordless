package ml.sniperbt.passwordless;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

public class Token implements Serializable {

    private final String token;
    private final OffsetDateTime expirationDate;

    public Token(final String token, final OffsetDateTime expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public OffsetDateTime getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token) &&
                Objects.equals(expirationDate, token1.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expirationDate);
    }

    @Override
    public String toString() {
        return token;
    }
}
