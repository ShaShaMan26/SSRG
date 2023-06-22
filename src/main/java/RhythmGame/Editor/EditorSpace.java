package RhythmGame.Editor;

import RhythmGame.Game.Song;
import RhythmGame.Instance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorSpace extends JPanel implements ActionListener {
    EditorTrack editorTrack;
    EditorNeedle editorNeedle;
    JButton testButton;
    JButton saveButton;
    EditorInstance editorInstance;

    EditorSpace(EditorInstance editorInstance) {
        this.editorInstance = editorInstance;

        editorTrack = new EditorTrack(editorInstance.displayDimension, editorInstance.song);
        editorNeedle = new EditorNeedle(editorTrack);

        this.add(editorTrack);
        this.add(editorNeedle);

        this.setLayout(null);

        int width = ((editorInstance.displayDimension.height / 9) * 16) / 8;
        int height = width / 2;
        int xCenter = editorInstance.displayDimension.width / 2 - (width / 2);
        int yPos = (int)(editorInstance.displayDimension.height / 1.25);
        testButton = new JButton();
        testButton.setFont(new Font("Arial", Font.PLAIN, height / 3));
        testButton.setText("Test Level");
        testButton.setBounds(xCenter, yPos, width, height);
        testButton.addActionListener(this);
        this.add(testButton);

        width = ((editorInstance.displayDimension.height / 9) * 16) / 10;
        height = width / 2;
        saveButton = new JButton();
        saveButton.setFont(new Font("Arial", Font.PLAIN, height / 3));
        saveButton.setText("Save Level");
        saveButton.setBounds(editorInstance.displayDimension.width - width, editorTrack.yPos -  height, width, height);
        saveButton.addActionListener(this);
        this.add(saveButton);

        this.setSize(editorInstance.displayDimension);
        this.setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Component component : this.getComponents()) {
            if (!(component instanceof JButton)) {
                component.paint(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == testButton) {
            editorInstance.wantsToTest = true;
        }
    }
}
