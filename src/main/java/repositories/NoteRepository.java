package repositories;

import model.Note;

import java.util.List;

public interface NoteRepository {
    public List<Note> getAllNotes();

    public Note getNoteById(String id) throws NoteDoesNotExistException;

    public void addNote (Note note);

    public void updateNote (String id, Note note) throws NoteDoesNotExistException;

    public void deleteNote (String id) throws NoteDoesNotExistException;

    public void deleteAllNotes();

    public void deleteAllNotesByUserId(String userId);

    public List<Note> getAllNotesByUserId(String userId);

    class NoteDoesNotExistException extends Exception {
        public NoteDoesNotExistException(String message) {
            super(message);
        }
    }
}
