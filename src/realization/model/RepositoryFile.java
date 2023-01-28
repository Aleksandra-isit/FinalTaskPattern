package realization.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository{
    private NotesMapper mapper = new NotesMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

        @Override
    public List<Observable> getAllNotes() {
        List<Observable> notes = new ArrayList<Observable>();
        List<String> dataFromFile = fileOperation.readAllLines();
            for (String line: dataFromFile) {
                notes.add(mapper.map(line));
            }
        return notes;
    }

    @Override
    public String CreateNote(Observable note) {
        List<Observable> notes = getAllNotes();
        int max = 0;
        for (Observable item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);
        notes.add(note);
        writeDown(notes);
        return id;
    }

    private void writeDown(List<Observable> notes) {
        List<String> lines = new ArrayList<String>();
        for (Observable item: notes) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void updateNote(Observable note) {
        List<Observable> notes = getAllNotes();
        Observable tmpNote = notes.stream().filter(i -> i.getId().equals(note.getId())).findFirst().get();
        tmpNote.setHeading(note.getHeading());
        tmpNote.setText(note.getText());
        writeDown(notes);
    }

    @Override
    public void deleteNote(String id) {
        List<Observable> notes = getAllNotes();
        notes.removeIf(item -> item.getId().equals(id));
        writeDown(notes);
    }
}
