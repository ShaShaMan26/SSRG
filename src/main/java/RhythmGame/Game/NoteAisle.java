package RhythmGame.Game;

import java.awt.*;
import java.util.ArrayList;

public class NoteAisle extends Component {
    Dimension displayDimensions;
    int height;
    int width;
    int id;
    int xPos;
    int receiverHeight;
    int receptionScore;
    boolean active = false;
    boolean received = false;
    ArrayList<Note> notes = new ArrayList<>();

    NoteAisle(Dimension displayDimensions, int id) {
        this.displayDimensions = displayDimensions;
        this.id = id;

        height = displayDimensions.height;

        // get 16:9 scaled width
        width = (displayDimensions.height / 9) * 16;
        // scale down width
        width = (int)(width * .35);
        // split into fours
        width /= 4;

        receiverHeight = width / 2;

        xPos = (displayDimensions.width / 2) - (width * 2) + (width * id);
    }

    public void update(int timeStamp, int amountOfTicks) {
        for (Note note : notes){
            note.move(timeStamp, amountOfTicks);
        }
        for (Note note : notes) {
            if (note.yPos > height
                    || note.yPos < -receiverHeight
                    || note.collected) {
                note.setDisplayed(false);
            } else {
                note.setDisplayed(true);
            }
        }
    }

    public int checkForNoteReception() {
        for (Note note : notes) {
            if (note.displayed
                    && note.yPos >= height - (receiverHeight * 2)
                    && note.yPos <= height
                    && active && !received) {
                receptionScore = 0;
                note.setCollected(true);
                received = true;

                if (note.yPos >= height - (receiverHeight * .75)) {
                    receptionScore = 5;
                    return 5;
                } else if (note.yPos >= height - (receiverHeight * .5)) {
                    receptionScore = 10;
                    return 10;
                } else if (note.yPos >= height - (receiverHeight * .25)) {
                    receptionScore = 15;
                    return 15;
                } else if (note.yPos == height - receiverHeight) {
                    receptionScore = 20;
                    return 20;
                } else if (note.yPos >= height - (receiverHeight * 1.25)) {
                    receptionScore = 15;
                    return 15;
                } else if (note.yPos >= height - (receiverHeight * 1.5)) {
                    receptionScore = 10;
                    return 10;
                } else if (note.yPos >= height - (receiverHeight * 1.75)) {
                    receptionScore = 5;
                    return 5;
                } else {
                    receptionScore = 1;
                    return 1;
                }
            }
        }
        return 0;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void setActive(boolean active) {
        this.active = active;
        if (!active) {
            received = false;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        // aisle
        g.setColor(new Color(96, 85, 96));
        g.fillRect(xPos, 0, width, height);

        // receiver
        if (active) {
            g.setColor(new Color(224, 188, 129));
        } else {
            g.setColor(new Color(150, 68, 218));
        }
        g.fillRect(xPos, height - receiverHeight, width, receiverHeight);

        double borderScale = .1;
        int borderNum = (int)(receiverHeight * borderScale);
        int innerXPos = xPos + borderNum;
        int innerYPos = (height - receiverHeight) + borderNum;

        g.setColor(new Color(255, 176, 244));
        g.fillRect(innerXPos, innerYPos, width - (borderNum * 2), receiverHeight - (borderNum * 2));

        // receptionScore
        if (receptionScore > 15) {
            g.setColor(Color.WHITE);
        } else if (receptionScore > 10) {
            g.setColor(Color.ORANGE);
        } else if (receptionScore > 5) {
            g.setColor(Color.YELLOW);
        } else if (receptionScore > 0) {
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.BLACK);
        }

        g.setFont(new Font("Arial", Font.PLAIN, height / 26));
        g.drawString("+" + receptionScore, innerXPos + (int)(receiverHeight / 2.75), innerYPos + (int)(receiverHeight / 1.75));

        // notes
        for (Note note : notes) {
            note.paint(g);
        }
    }
}
