package io.pivotal.pal.tracker;

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

    public TimeEntriesController(JdbcTimeEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        TimeEntry createdTimeEntry = repository.create(timeEntry);
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<>(repository.list(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry>read(@PathVariable Long id){
        TimeEntry timeEntry = repository.get(id);

        if (timeEntry != null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = repository.update(id, timeEntry);

        if (updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
