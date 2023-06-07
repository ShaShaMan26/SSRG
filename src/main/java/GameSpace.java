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

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Component component : this.getComponents()) {
            component.paint(g);
        }
    }
}
