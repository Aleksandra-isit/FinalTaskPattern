package realization.views;

import realization.controllers.NoteController;
import realization.model.Anecdote;
import realization.model.Note;
import realization.model.Observable;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private NoteController noteController;

    public Menu(NoteController noteController) {
        this.noteController = noteController;
    }


    public void run() {
        Commands com = Commands.NONE;
        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try {
                switch (com) {
                    case CREATE:
                        Observable o;
                        int type = typeOfCreationObject();
                        String title = "";
                        if (type == 1){
                            title = "записки";
                        }
                        else {
                            title = "анекдота";
                        }

                        String heading = prompt("Заголовок" + title + " : ");
                        String text = prompt("Текст" + title + ": ");

                        if (type == 1){
                            noteController.saveNote(new Note(heading, text));
                        }
                        else {
                            noteController.saveNote(new Anecdote(heading, text));
                        }
                        break;
                    case READ:
                        String id = prompt("Идентификатор данных: ");
                        Observable item = noteController.readNote(id);
                        System.out.println(item);
                        break;
                    case LIST:
                        List<Observable> items = noteController.readNotes();
                        items.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case UPDATE:
                        String numId = prompt("Какой контакт вы хотите изменить? Введите номер ID:");
                        noteController.validationID(numId);
                        noteController.updateNote(numId, createItem());
                        break;
                    case DELETE:
                        String idToDelete = prompt("Какой контакт вы хотите удалить? Введите номер ID:");
                        noteController.validationID(idToDelete);
                        noteController.deleteNote(idToDelete);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Oopsie!\n" + e.getMessage());
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private Observable createItem() {
        int type = typeOfCreationObject();
        String title = "";
        if (type == 1){
            title = "записки";
        }
        else {
            title = "анекдота";
        }

        String heading = prompt("Заголовок" + title + " : ");
        String text = prompt("Текст" + title + ": ");

        if (type == 1){
            return new Note(heading, text);
        }
        else {
            return new Anecdote(heading, text);
        }
    }

    private int typeOfCreationObject() {
        String input = prompt("Какой объект вы хотите создать?\n1. Заметка\t2. Анекдот");
        if (tryParseInt(input, 0) == 1){
            return 1;
        }
        if (tryParseInt(input, 0) == 2){
            return 2;
        }
        return typeOfCreationObject();
    }

    private int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
