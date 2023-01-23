import realization.controllers.NoteController;
import realization.model.FileOperation;
import realization.model.FileOperationImpl;
import realization.model.Repository;
import realization.model.RepositoryFile;
import realization.views.Menu;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("notes.txt");
        Repository repository = new RepositoryFile(fileOperation);
        NoteController controller = new NoteController(repository);
        Menu menu = new Menu(controller);
        menu.run();
    }
}