package controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Note;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.NoteService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NoteService noteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Iterable<Note> findAll() {
        System.out.println("Finding all notes");
        log.info("Found all notes: {}", noteService.findAll());
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Note findById(@PathVariable @NotNull Integer id) {
        log.info("Finding note by id {}", id);
        return noteService.findById(id);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    Collection<Note> findAllByUserId(@PathVariable @NotNull String userId) {
        return noteService.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void add(@RequestBody @NotNull Note note) {
        log.info("Adding note {}", note);
        noteService.add(note);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable @NotNull Integer id, @RequestBody @NotNull Note note) {
        noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @NotNull Integer id) {
        noteService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAll() {
        noteService.deleteAll();
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAllByUserId(@PathVariable @NotNull String userId) {
        noteService.deleteAllByUserId(userId);
    }
}
