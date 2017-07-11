package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by pivotal on 2017-07-11.
 */
@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
    private static final int MAX_TIME_ENTRIES = 5;
    public TimeEntryRepository repository;

    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepo) {
        this.repository = timeEntryRepo;
    }

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();

        if (repository.list().size() < MAX_TIME_ENTRIES) {
            builder.up();
        } else {
            builder.down();
        }
        return builder.build();
    }
}
