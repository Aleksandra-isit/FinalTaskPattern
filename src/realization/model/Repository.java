package realization.model;

import java.util.List;

public interface Repository {
    List<Observable> getAllNotes();
    String CreateNote(Observable note);
    void updateNote(Observable note);
    void deleteNote(String id);
}
