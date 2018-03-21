package ml.sniperbt.passwordless;

import java.io.Serializable;

public class Token implements Serializable {

    private final String token;

    public Token(final String token) {
        this.token = token;
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
