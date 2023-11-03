package repositories;

import model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepositoryPostgres extends JpaRepository<Note, Integer> {

}
