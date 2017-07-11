package io.pivotal.pal.tracker;

import java.util.List;

/**
 * Created by pivotal on 2017-07-11.
 */
public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);
    TimeEntry get(Long id);
    List<TimeEntry> list();
    TimeEntry update(Long id, TimeEntry timeEntry);
    void delete(Long id);
}
