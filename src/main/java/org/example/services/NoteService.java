package services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Note;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repositories.NoteRepositoryPostgres;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteService {
    public static final String NOTE_WITH_ID_WAS_NOT_FOUND = "Note with id %s was not found";
    private final NoteRepositoryPostgres noteRepository;

    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note findById(Integer id) {
        return noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOTE_WITH_ID_WAS_NOT_FOUND.formatted(id)));
    }

    public void add(@NotNull Note note) {
        if (note.getId() != null) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Id of Note should not be provided");
        }

        noteRepository.save(note);
    }

    public void update(Integer id, Note note) {
        var noteToUpdate = noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOTE_WITH_ID_WAS_NOT_FOUND.formatted(id)));
        noteToUpdate.updateNote(note);
        noteRepository.save(noteToUpdate);
    }

    public void delete(Integer id) {
        noteRepository.delete(noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOTE_WITH_ID_WAS_NOT_FOUND.formatted(id))));
    }

    public void deleteAll() {
        noteRepository.deleteAll();
    }

    public void deleteAllByUserId(String userId) {
        noteRepository.findAll().forEach(note -> {
            if (note.getUserId().equals(userId)) {
                noteRepository.delete(note);
            }
        });
    }

    public Collection<Note> findAllByUserId(String userId) {
        var notesByUserId = new java.util.ArrayList<Note>();
        for (Note note : noteRepository.findAll()) {
            if (note.getUserId().equals(userId)) {
                notesByUserId.add(note);
            }
        }

        return notesByUserId;
    }
}
