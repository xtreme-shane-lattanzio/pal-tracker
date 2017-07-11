package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pivotal on 2017-07-11.
 */

@RestController
@RequestMapping("/timeEntries")
public class TimeEntriesController {

    private TimeEntryRepository repository;
    private final CounterService counter;
    private final GaugeService gauge;

    public TimeEntriesController(JdbcTimeEntryRepository repository, CounterService counter, GaugeService gauge) {
        this.repository = repository;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry createdTimeEntry = repository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", repository.list().size());
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(repository.list(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry>read(@PathVariable Long id){
        TimeEntry timeEntry = repository.get(id);

        if (timeEntry != null) {
            counter.increment("TimeEntry.read");
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = repository.update(id, timeEntry);

        if (updatedTimeEntry != null) {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        repository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", repository.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
