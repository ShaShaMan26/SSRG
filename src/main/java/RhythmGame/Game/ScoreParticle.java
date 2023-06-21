package RhythmGame.Game;

import java.awt.*;

public class ScoreParticle extends Component {
    int value;
    long receptionTime = System.currentTimeMillis();
    public int elapsedReceptionTime = 100;

    ScoreParticle(int value) {
        this.value = value;
    }

    public void paint(Graphics g, NoteAisle parentAisle) {
        elapsedReceptionTime = (int)((receptionTime + 600) - System.currentTimeMillis());

        int opacityLevel;
        if (elapsedReceptionTime < 0) {
            opacityLevel = 0;
        } else opacityLevel = Math.min(elapsedReceptionTime, 255);

        if (value > 15) {
            g.setColor(new Color(121, 0, 255, opacityLevel));
        } else if (value > 10) {
            g.setColor(new Color(72, 255, 0, opacityLevel));
        } else if (value > 5) {
            g.setColor(new Color(0, 196, 255, opacityLevel));
        } else if (value > 1) {
            g.setColor(new Color(255, 234, 0, opacityLevel));
        } else {
            g.setColor(new Color(255, 0, 47, opacityLevel));
        }

        g.setFont(new Font("Arial", Font.PLAIN, parentAisle.height / 26));
        g.drawString("+" + value, parentAisle.xPos + (parentAisle.receiverHeight / 2),
                parentAisle.height - (int)(parentAisle.receiverHeight / 2.75) + (elapsedReceptionTime / 6) - 100);
    }
}
