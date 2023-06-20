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
    boolean active = false;
    boolean received = false;
    ArrayList<Note> notes = new ArrayList<>();
    ArrayList<ScoreParticle> scoreParticles = new ArrayList<>();

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
                note.setCollected(true);
                received = true;

                if (note.yPos >= height - (receiverHeight * .75)) {
                    scoreParticles.add(new ScoreParticle(5));
                    return 5;
                } else if (note.yPos >= height - (receiverHeight * .5)) {
                    scoreParticles.add(new ScoreParticle(10));
                    return 10;
                } else if (note.yPos >= height - (receiverHeight * .25)) {
                    scoreParticles.add(new ScoreParticle(15));
                    return 15;
                } else if (note.yPos == height - receiverHeight) {
                    scoreParticles.add(new ScoreParticle(20));
                    return 20;
                } else if (note.yPos >= height - (receiverHeight * 1.25)) {
                    scoreParticles.add(new ScoreParticle(15));
                    return 15;
                } else if (note.yPos >= height - (receiverHeight * 1.5)) {
                    scoreParticles.add(new ScoreParticle(10));
                    return 10;
                } else if (note.yPos >= height - (receiverHeight * 1.75)) {
                    scoreParticles.add(new ScoreParticle(5));
                    return 5;
                } else {
                    scoreParticles.add(new ScoreParticle(1));
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
        g.setColor(new Color(87, 85, 98));
        g.fillRect(xPos, 0, width, height);

        // receiver
        if (active) {
            g.setColor(new Color(168, 150, 36));
        } else {
            g.setColor(new Color(33, 168, 11));
        }
        g.fillRect(xPos, height - receiverHeight, width, receiverHeight);

        double borderScale = .1;
        int borderNum = (int)(receiverHeight * borderScale);
        int innerXPos = xPos + borderNum;
        int innerYPos = (height - receiverHeight) + borderNum;

        g.setColor(new Color(147, 199, 140));
        g.fillRect(innerXPos, innerYPos, width - (borderNum * 2), receiverHeight - (borderNum * 2));

        // scoreParticles
        scoreParticles.removeIf(scoreParticle -> scoreParticle.elapsedReceptionTime < 1);

        for (ScoreParticle scoreParticle : scoreParticles) {
            scoreParticle.paint(g, this);
        }

        // notes
        for (Note note : notes) {
            note.paint(g);
        }
    }
}
