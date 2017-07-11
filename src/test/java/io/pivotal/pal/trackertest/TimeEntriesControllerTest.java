package io.pivotal.pal.trackertest;

import io.pivotal.pal.tracker.JdbcTimeEntryRepository;
import io.pivotal.pal.tracker.TimeEntriesController;
import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import static java.util.Arrays.asList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by pivotal on 2017-07-11.
 */
public class TimeEntriesControllerTest {
    JdbcTimeEntryRepository timeEntryRepository;
    TimeEntriesController controller;

    @Before
    public void setUp() throws Exception {
        timeEntryRepository = mock(JdbcTimeEntryRepository.class);
        controller = new TimeEntriesController(timeEntryRepository);
    }

    @Test
    public void testCreate() throws Exception {
        TimeEntry expected = new TimeEntry(22, 33, "woot", 44);
        doReturn(expected).when(timeEntryRepository).create(any(TimeEntry.class));

        ResponseEntity responseEntity = controller.create(new TimeEntry(1L, 22, 33, "woot", 44));

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testList() throws Exception {
        TimeEntry entry1 = new TimeEntry(22, 33, "woot", 44);
        TimeEntry entry2 = new TimeEntry(55, 66, "yay", 77);

        List<TimeEntry> expected = asList(entry1,
                entry2);

        doReturn(expected).when(timeEntryRepository).list();

        ResponseEntity responseEntity = controller.list();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRead() throws Exception {
        TimeEntry expected = new TimeEntry(1L,22, 33, "woot", 44);
        doReturn(expected).when(timeEntryRepository).get(1L);

        ResponseEntity responseEntity = controller.read(1L);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testUpdate() throws Exception {
        TimeEntry expected = new TimeEntry(1L,22, 33, "woot", 44);
        doReturn(expected).when(timeEntryRepository).update(eq(2L), any(TimeEntry.class));

        ResponseEntity responseEntity = controller.update(2L, expected);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expected);
    }

    @Test
    public void testDelete() throws Exception {
        ResponseEntity responseEntity = controller.delete(1L);
        verify(timeEntryRepository).delete(1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
