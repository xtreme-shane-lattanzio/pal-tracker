package io.pivotal.pal.trackertest;

import io.pivotal.pal.tracker.InMemoryTimeEntryRepository;
import io.pivotal.pal.tracker.TimeEntry;
import org.junit.Test;

import java.util.List;
import static java.util.Arrays.asList;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by pivotal on 2017-07-11.
 */
public class InMemoryTimeEntryRepositoryTest {
    @Test
    public void create() throws Exception {
        InMemoryTimeEntryRepository repository = new InMemoryTimeEntryRepository();
        TimeEntry timeEntry = repository.create(new TimeEntry(22, 33, "woot", 44));

        TimeEntry expected = new TimeEntry(1L,22, 33, "woot", 44);
        assertThat(timeEntry).isEqualTo(expected);

        TimeEntry getEntry = repository.get(timeEntry.getId());
        assertThat(getEntry).isEqualTo(expected);
    }

    @Test
    public void get() throws Exception {
        InMemoryTimeEntryRepository repository = new InMemoryTimeEntryRepository();
        repository.create(new TimeEntry(22, 33, "woot", 44));

        TimeEntry expected = new TimeEntry(1L, 22, 33, "woot", 44);
        TimeEntry getEntry = repository.get(1L);
        assertThat(getEntry).isEqualTo(expected);
    }

    @Test
    public void list() throws Exception {
        TimeEntry entry1 = new TimeEntry(22, 33, "woot", 44);
        TimeEntry entry2 = new TimeEntry(55, 66, "yay", 77);

        InMemoryTimeEntryRepository repository = new InMemoryTimeEntryRepository();
        repository.create(entry1);
        repository.create(entry2);

        List<TimeEntry> expected = asList(new TimeEntry(1L,22, 33, "woot", 44), new TimeEntry(2L,55, 66, "yay", 77));
        assertThat(repository.list()).isEqualTo(expected);
    }

    @Test
    public void update() throws Exception {
        InMemoryTimeEntryRepository repository = new InMemoryTimeEntryRepository();
        TimeEntry entry1 = new TimeEntry(22, 33, "woot", 44);
        repository.create(entry1);

        assertThat(repository.update(2L, entry1)).isEqualTo(new TimeEntry(2L,22, 33, "woot", 44));
    }

    @Test
    public void delete() throws Exception {
        InMemoryTimeEntryRepository repository = new InMemoryTimeEntryRepository();
        TimeEntry entry1 = new TimeEntry(22, 33, "woot", 44);
        repository.create(entry1);

        repository.delete(entry1.getId());
        assertThat(repository.list()).isEmpty();
    }
}
