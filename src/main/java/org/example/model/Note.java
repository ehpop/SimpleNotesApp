package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(force = true)
@Table(name = "notes")
public class Note {
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    @Id
    private Integer id;

    private final String userId;

    public Note(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\n" +
                "Content: " + this.content + "\n" +
                "ID: " + this.id + "\n" +
                "User ID: " + this.userId + "\n";
    }

    public void updateNote(@NotNull Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getId() == null) {
            return false;
        }
        if (!(obj instanceof Note)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (((Note) obj).getId() == null) {
            return false;
        }

        return this.getId().equals(((Note) obj).getId());
    }

    @Override
    public int hashCode() {
        if (this.getId() == null) return 0;
        return this.getId().hashCode();
    }
}
