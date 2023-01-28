package realization.controllers;

import realization.model.Note;
import realization.model.Observable;
import realization.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class NoteController {
    private final Repository repository;

    public NoteController(Repository repository) {
        this.repository = repository;
    }

    public void saveNote(Observable note){
        repository.CreateNote(note);
    }

    public Observable readNote(String idToRead) throws Exception{
        List<Observable> notes = repository.getAllNotes();
        for (Observable item: notes) {
            if (item.getId().equals(idToRead)){
                return item;
            }
        }
        throw new Exception("User not found");
    }

    public void validationID (String inputId) throws Exception {
        List<Observable> notes = repository.getAllNotes();
        for (Observable item : notes) {
            if (item.getId().equals(inputId))
                return;
        }
        throw new Exception("No such ID here");
    }

    public void updateNote(String idToUpdate, Observable updateNote) throws Exception{
        validationID(idToUpdate);
        updateNote.setId(idToUpdate);
        repository.updateNote(updateNote);
    }

    public List<Observable> readNotes(){
        return repository.getAllNotes();
    }

    public void deleteNote(String idToDelete){
        repository.deleteNote(idToDelete);
    }
}
