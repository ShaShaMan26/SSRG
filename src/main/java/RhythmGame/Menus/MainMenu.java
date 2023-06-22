package RhythmGame.Menus;

import RhythmGame.Instance;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends JPanel implements ActionListener {
    Instance instance;
    JButton playLevelButton;
    JButton editLevelButton;
    ArrayList<JButton> buttons = new ArrayList<>();

    public MainMenu(Instance instance) {
        this.instance = instance;

        this.setLayout(null);

        buttons.add(playLevelButton);
        buttons.add(editLevelButton);

        int width = ((instance.DISPLAY_DIMENSIONS.height / 9) * 16) / 8;
        int height = width / 2;
        int xCenter = instance.DISPLAY_DIMENSIONS.width / 2 - (width / 2);
        int yCenter = instance.DISPLAY_DIMENSIONS.height / 2 - (height / 2);

        playLevelButton = new JButton();
        playLevelButton.setFont(new Font("Arial", Font.PLAIN, height / 3));
        playLevelButton.setText("Play Level");
        playLevelButton.addActionListener(this);
        playLevelButton.setBounds(xCenter - (width / 2), yCenter, width, height);
        this.add(playLevelButton);
        editLevelButton = new JButton();
        editLevelButton.setFont(new Font("Arial", Font.PLAIN, height / 3));
        editLevelButton.setText("Edit Level");
        editLevelButton.addActionListener(this);
        editLevelButton.setBounds(xCenter + (width / 2), yCenter, width, height);
        this.add(editLevelButton);

        this.setSize(instance.DISPLAY_DIMENSIONS);
        this.setBackground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playLevelButton) {
            try {
                instance.goToGame();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == editLevelButton) {
            try {
                instance.goToEditor();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
