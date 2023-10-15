package controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.NoteService;

import java.util.Collection;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NoteService noteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Map<String, Note> findAll() {
        log.info("Found all notes: {}", noteService.findAll());
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Note findById(@PathVariable String id) {
        log.info("Finding note by id {}", id);
        return noteService.findById(id);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    Collection<Note> findAllByUserId(@PathVariable String userId) {
        return noteService.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void add(@RequestBody Note note) {
        log.info("Adding note {}", note);
        noteService.add(note);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Note note, @PathVariable String id) {
        noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        noteService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAll() {
        noteService.deleteAll();
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAllByUserId(@PathVariable String userId) {
        noteService.deleteAllByUserId(userId);
    }
}
