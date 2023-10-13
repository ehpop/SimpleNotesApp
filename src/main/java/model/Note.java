package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class Note {
    @Setter private String title;
    @Setter private String content;
    @Setter private String id;
    private final Date creationDate;
    private final String userId;

    public Note (String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.creationDate = new Date();
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "Title: " + this.title + "\n" +
                "Content: " + this.content + "\n" +
                "Creation Date: " + this.creationDate + "\n" +
                "ID: " + this.id + "\n" +
                "User ID: " + this.userId + "\n";
    }

    public void updateNote(Note note) {
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}
