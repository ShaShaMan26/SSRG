package RhythmGame.Menus;

import javax.swing.*;
import java.awt.*;

public class SongButton extends JButton {
    public String songName;

    SongButton(MainMenu mainMenu, String songName, int yOffset) {
        this.songName = songName;

        int width = ((mainMenu.instance.DISPLAY_DIMENSIONS.height / 9) * 16) / 2;
        int height = width / 8;

        this.setFont(new Font("Arial", Font.PLAIN, (height / 4)));
        this.setText(songName);
        this.addActionListener(mainMenu);
        this.setBounds(0, yOffset*height, width, height);
    }
}
