package realization.model;

public class Note {
    private String id = "";
    private String heading;
    private String text;

    public Note(String heading, String text) {
        this.heading = heading;
        this.text = text;
    }

    public Note(String id, String heading, String text) {
        this.id = id;
        this.heading = heading;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор - %s\nЗаголовок - %s\nТекст записки - %s\n", id, heading, text);
    }
}
