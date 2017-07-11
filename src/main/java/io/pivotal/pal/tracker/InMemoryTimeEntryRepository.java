package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pivotal on 2017-07-11.
 */
public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private HashMap<Long, TimeEntry> entryMap = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(entryMap.size() + 1);
        entryMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry get(Long id) {
        return entryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(entryMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        entryMap.replace(id, timeEntry);
        timeEntry.setId(id);
        return timeEntry;
    }

    @Override
    public void delete(Long id) {
        entryMap.remove(id);
    }
}
