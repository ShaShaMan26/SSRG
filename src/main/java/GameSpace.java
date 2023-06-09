import javax.swing.*;
import java.awt.*;

public class GameSpace extends JPanel {
    NoteAisle noteAisle1, noteAisle2, noteAisle3, noteAisle4;
    NoteReceiverController noteReceiverController = new NoteReceiverController(this);

    GameSpace(Dimension displayDimensions) {
        this.setSize(displayDimensions);
        this.setBackground(Color.BLACK);

        noteAisle1 = new NoteAisle(displayDimensions, 0);
        noteAisle2 = new NoteAisle(displayDimensions, 1);
        noteAisle3 = new NoteAisle(displayDimensions, 2);
        noteAisle4 = new NoteAisle(displayDimensions, 3);
        this.add(noteAisle1);
        this.add(noteAisle2);
        this.add(noteAisle3);
        this.add(noteAisle4);
    }

    public void addNote(Note note) {
        switch(note.id) {
            case 0:
                note.setParentAisle(noteAisle1);
                noteAisle1.addNote(note);
                break;
            case 1:
                note.setParentAisle(noteAisle2);
                noteAisle2.addNote(note);
                break;
            case 2:
                note.setParentAisle(noteAisle3);
                noteAisle3.addNote(note);
                break;
            case 3:
                note.setParentAisle(noteAisle4);
                noteAisle4.addNote(note);
                break;
        }
    }

    public void updateNoteAisles(int timeStamp, int amountOfTicks) {
        noteAisle1.update(timeStamp, amountOfTicks);
        noteAisle2.update(timeStamp, amountOfTicks);
        noteAisle3.update(timeStamp, amountOfTicks);
        noteAisle4.update(timeStamp, amountOfTicks);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Component component : this.getComponents()) {
            component.paint(g);
        }
    }
}
