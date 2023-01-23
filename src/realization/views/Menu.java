package realization.views;

import realization.controllers.NoteController;
import realization.model.Note;

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
                        String heading = prompt("Заголовок записки: ");
                        String text = prompt("Текст записки: ");
                        noteController.saveNote(new Note(heading, text));
                        break;
                    case READ:
                        String id = prompt("Идентификатор записки: ");
                        Note note = noteController.readNote(id);
                        System.out.println(note);
                        break;
                    case LIST:
                        List<Note> notes = noteController.readNotes();
                        notes.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case UPDATE:
                        String numId = prompt("Какой контакт вы хотите изменить? Введите номер ID:");
                        noteController.validationID(numId);
                        noteController.updateNote(numId, createNote());
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

        private String prompt (String message){
            Scanner in = new Scanner(System.in);
            System.out.print(message);
            return in.nextLine();
        }

        private Note createNote () {
            String headline = prompt("Заголовок: ");
            String text = prompt("Текст записки: ");
            return new Note(headline, text);
        }

    }
