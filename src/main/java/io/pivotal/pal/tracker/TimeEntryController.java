package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry);
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        return timeEntry == null ? notFound().build() : ok(timeEntry);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ok(timeEntryRepository.list());
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        return timeEntry == null ? notFound().build() : ok(timeEntry);
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return noContent().build();
    }
}