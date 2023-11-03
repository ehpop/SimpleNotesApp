package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    private String title;
    private String content;
    private String userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Note(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public void updateNote(@NotNull Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}
