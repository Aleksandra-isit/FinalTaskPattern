package realization.model;

public class NotesMapper {
    public String map(Observable user) {
        return String.format("%s~%s~%s", user.getId(), user.getHeading(), user.getText());
    }

    public Note map(String line) {
        String[] lines = line.split("~");
        return new Note(lines[0], lines[1], lines[2]);
    }
}
