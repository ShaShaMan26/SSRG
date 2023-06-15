package RhythmGame.Game;

import java.awt.*;

public class ScoreDisplay extends Component {
    int score;
    int xPos;
    int yPos;

    ScoreDisplay(Dimension displayDimensions) {
        xPos = displayDimensions.width;
        yPos = 50;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic sans", Font.PLAIN, 45));

        int width = g.getFontMetrics().stringWidth(String.valueOf(score));
        g.drawString(String.valueOf(score), xPos - width, yPos);
    }
}
