package ml.sniperbt.passwordless;


import java.time.temporal.TemporalUnit;

public final class TTL {

    private final long value;
    private final TemporalUnit timeUnit;

    public TTL(final int value, final TemporalUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    public long getValue() {
        return value;
    }

    public TemporalUnit getTimeUnit() {
        return timeUnit;
    }
}
