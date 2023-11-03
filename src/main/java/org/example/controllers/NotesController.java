package org.example.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Note;
import org.example.services.NoteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        log.debug("Before finding all notes");
        Iterable<Note> notes = noteService.findAll();
        log.debug("Found all notes: {}", notes);
        return notes;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Note findById(@PathVariable @NotNull Integer id) {
        log.debug("Finding note by id {}", id);
        Note note = noteService.findById(id);
        log.debug("Found note by id {}: {}", id, note);
        return note;
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    Collection<Note> findAllByUserId(@PathVariable @NotNull String userId) {
        log.debug("Finding all notes by user ID: {}", userId);
        Collection<Note> notes = noteService.findAllByUserId(userId);
        log.debug("Found notes by user ID {}: {}", userId, notes);
        return notes;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void add(@RequestBody @NotNull Note note) {
        log.debug("Adding note: {}", note);
        noteService.add(note);
        log.debug("Note added: {}", note);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void update(@PathVariable @NotNull Integer id, @RequestBody @NotNull Note note) {
        log.debug("Updating note with id {}: {}", id, note);
        noteService.update(id, note);
        log.debug("Note with id {} updated", id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @NotNull Integer id) {
        log.debug("Deleting note with id: {}", id);
        noteService.delete(id);
        log.debug("Note with id {} deleted", id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAll() {
        log.debug("Deleting all notes");
        noteService.deleteAll();
        log.debug("All notes deleted");
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAllByUserId(@PathVariable @NotNull String userId) {
        log.debug("Deleting all notes by user ID: {}", userId);
        noteService.deleteAllByUserId(userId);
        log.debug("All notes by user ID {} deleted", userId);
    }
}
