package realization.model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    String CreateNote(Note note);
    void updateNote(Note note);
    void deleteNote(String id);
}
