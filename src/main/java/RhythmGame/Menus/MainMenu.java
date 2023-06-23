package RhythmGame.Menus;

import RhythmGame.Instance;
import org.json.simple.parser.ParseException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenu extends JPanel implements ActionListener {
    Instance instance;
    JButton playLevelButton;
    JButton editLevelButton;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<SongButton> songButtons = new ArrayList<>();

    public MainMenu(Instance instance) {
        this.instance = instance;

        this.setLayout(null);

        buttons.add(playLevelButton);
        buttons.add(editLevelButton);

        int width = ((instance.DISPLAY_DIMENSIONS.height / 9) * 16) / 6;
        int height = width / 3;
        int xCenter = instance.DISPLAY_DIMENSIONS.width / 2;
        int yCenter = instance.DISPLAY_DIMENSIONS.height / 2;
        int yTop = instance.DISPLAY_DIMENSIONS.height / 6;

        JLabel songLabel = new JLabel();
        songLabel.setFont(new Font("Monospaced", Font.BOLD, instance.DISPLAY_DIMENSIONS.height / 28));
        songLabel.setForeground(Color.WHITE);
        songLabel.setHorizontalTextPosition(JLabel.CENTER);
        songLabel.setVerticalTextPosition(JLabel.BOTTOM);
        int iconSideLength = instance.DISPLAY_DIMENSIONS.height / 3;
        songLabel.setIcon(new ImageIcon(instance.song.icon.getImage().getScaledInstance(iconSideLength, iconSideLength, Image.SCALE_DEFAULT)));
        songLabel.setBounds(xCenter - (iconSideLength / 2) + (xCenter / 2), yTop - height, iconSideLength, instance.DISPLAY_DIMENSIONS.height);
        songLabel.setText("<html><p style=\"text-align: center;\">" + instance.song.name + "<br><i>" + instance.song.author + "</i></p></html>");
        songLabel.setVerticalAlignment(JLabel.NORTH);
        this.add(songLabel);

        playLevelButton = new JButton();
        playLevelButton.setFont(new Font("Arial", Font.PLAIN, (int)(height / 2.5)));
        playLevelButton.setText("Play Level");
        playLevelButton.addActionListener(this);
        playLevelButton.setBounds(xCenter + (xCenter / 2) - (width / 2), yTop + yCenter, width, height);
        this.add(playLevelButton);
        editLevelButton = new JButton();
        editLevelButton.setFont(new Font("Arial", Font.PLAIN, (int)(height / 2.5)));
        editLevelButton.setText("Edit Level");
        editLevelButton.addActionListener(this);
        editLevelButton.setBounds(xCenter + (xCenter / 2) - (width / 2), yTop + yCenter + (height / 2)*2, width, height);
        this.add(editLevelButton);

        this.setSize(instance.DISPLAY_DIMENSIONS);
        this.setBackground(Color.BLACK);

        JPanel songsPanel = new JPanel();
        songsPanel.setLayout(null);
        songsPanel.setBackground(Color.DARK_GRAY);
        int i = 0;
        for (String songName : Objects.requireNonNull(new File(System.getenv("APPDATA") + "\\SSRG\\songs").list())) {
            SongButton songButton = new SongButton(this, songName, i);

            songButtons.add(songButton);
            songsPanel.add(songButton);
            i++;
        }
        int songButtonHeight = (((instance.DISPLAY_DIMENSIONS.height / 9) * 16) / 2) / 8;
        songsPanel.setPreferredSize(new Dimension(width, songButtons.size()*songButtonHeight));
        JScrollPane scrollPane = new JScrollPane(songsPanel);
        scrollPane.setSize(xCenter, instance.DISPLAY_DIMENSIONS.height);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(songButtonHeight / 2);
        this.add(scrollPane);
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
        } else {
            for (SongButton songButton : songButtons) {
                if (e.getSource() == songButton) {
                    try {
                        instance.setLevelFile(new File(System.getenv("APPDATA")+"\\SSRG\\songs\\" + songButton.songName + "\\songData.json"));
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
}
