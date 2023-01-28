package realization.model;

public interface Observable {
    void show(Observer o, String rate);
    String getId();
    String getText();
    String getHeading();
    void setText(String text);
    void setHeading(String heading);
    void setId(String id);
}
