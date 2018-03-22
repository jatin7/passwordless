package ml.sniperbt.passwordless;

import java.time.temporal.ChronoUnit;

public interface Configuration {

    default int getTokenByteSize() { return 64; }

    default TTL getTTL() { return new TTL(5, ChronoUnit.MINUTES); }


}
