package repositories.impl;

import lombok.extern.slf4j.Slf4j;
import model.Note;
import org.springframework.stereotype.Component;
import repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NoteRepositoryImpl implements NoteRepository {
    private final List<Note> notes = new ArrayList<>();
    private Integer id = 0;
    private static final String NODE_NOT_FOUND_MSG = "Note with id %s does not exist";
    private static final String INVALID_ID_MSG = "Invalid id %s";

    /**
     * This method returns all the notes
     *
     * @return a list of all the notes
     */
    @Override
    public List<Note> getAllNotes() {
        return notes;
    }

    /**
     * This method returns a note by its id
     *
     * @param id the id of the note
     * @return the note with the given id
     * @throws NoteDoesNotExistException if the note does not exist
     * @throws NumberFormatException if the id is not a number
     */
    @Override
    public Note getNoteById(String id) throws NoteDoesNotExistException, NumberFormatException {
        try {
            return notes.get(Integer.parseInt(id));
        } catch (IndexOutOfBoundsException e) {
            throw new NoteDoesNotExistException(NODE_NOT_FOUND_MSG.formatted(id));
        } catch (NumberFormatException e) {
            throw new NoteDoesNotExistException(INVALID_ID_MSG.formatted(id));
        }
    }

    /**
     * This method adds a note
     *
     * @param note the note to be added
     */
    @Override
    public void addNote(Note note) {
        note.setId(id.toString());
        notes.add(note);
        id++;
    }

    /**
     * This method updates a note
     *
     * @param note the note to be updated
     * @throws NoteDoesNotExistException if the note does not exist
     * @throws NumberFormatException if the id is not a number
     */
    @Override
    public void updateNote(String id, Note note) throws NoteDoesNotExistException, NumberFormatException {
        try{
            Note foundNote = notes.get(Integer.parseInt(note.getId()));
            foundNote.updateNote(note);
        } catch (IndexOutOfBoundsException e) {
            throw new NoteDoesNotExistException(NODE_NOT_FOUND_MSG.formatted(note.getId()));
        } catch (NumberFormatException e) {
            throw new NoteDoesNotExistException(INVALID_ID_MSG.formatted(note.getId()));
        }
    }

    /**
     * This method deletes a note by its id
     *
     * @param id the id of the note
     * @throws NoteDoesNotExistException if the note does not exist
     * @throws NumberFormatException if the id is not a number
     */
    @Override
    public void deleteNote(String id) throws NoteDoesNotExistException, NumberFormatException {
        try {
            notes.remove(Integer.parseInt(id));
        } catch (IndexOutOfBoundsException e) {
            throw new NoteDoesNotExistException(NODE_NOT_FOUND_MSG.formatted(id));
        } catch (NumberFormatException e) {
            throw new NoteDoesNotExistException(INVALID_ID_MSG.formatted(id));
        }
    }

    /**
     * This method deletes all the notes
     */
    @Override
    public void deleteAllNotes() {
        notes.clear();
    }

    /**
     * This method deletes all the notes that belong to a user
     *
     * @param userId the id of the user
     */
    @Override
    public void deleteAllNotesByUserId(String userId) {
        notes.removeIf(note -> note.getUserId().equals(userId));
    }

    /**
     * This method returns all the notes that belong to a user
     *
     * @param userId the id of the user
     * @return a list of notes that belong to the user
     */
    @Override
    public List<Note> getAllNotesByUserId(String userId) {
        return notes.stream().filter(note -> note.getUserId().equals(userId)).toList();
    }

}
