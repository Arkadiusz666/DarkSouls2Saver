package windows;

import functionality.FileOperator;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Arkadiusz666 on 2016-11-15.
 */
public class MainWindow extends JFrame {
    private JPanel rootPanel;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel saveGameDateLabel;
    private JLabel backupSaveGameDateLabel;
    private JCheckBox playSoundWhenGameIsSavedCheckBox;

    public MainWindow() {

        super("Dark Souls 2 Savior");
//        setResizable(false);
        setContentPane(rootPanel);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        saveGameDateLabel.setText(FileOperator.getGameSaveDate());
        backupSaveGameDateLabel.setText(FileOperator.getBackupSaveDate());
        Color defaultBgColor = rootPanel.getBackground();
        saveButton.addActionListener(e -> {
            FileOperator.saveGame();
            backupSaveGameDateLabel.setText(FileOperator.getBackupSaveDate());

            rootPanel.setBackground(defaultBgColor);
        });
        loadButton.addActionListener(e -> {
            FileOperator.loadGame();
            rootPanel.setBackground(defaultBgColor);
        });

        Timer SimpleTimer = new Timer(500, new ActionListener() {
            String lastDate = FileOperator.getGameSaveDate();

            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameDateLabel.setText(FileOperator.getGameSaveDate());
                if (!lastDate.equals(saveGameDateLabel.getText())) {
                    lastDate = saveGameDateLabel.getText();
                    if (playSoundWhenGameIsSavedCheckBox.isSelected()) {
                        playSound();
                    }
                }
                if (!saveGameDateLabel.getText().equals(backupSaveGameDateLabel.getText())) {
                    rootPanel.setBackground(Color.red);
                }
            }
        });
        SimpleTimer.start();
    }

    private void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
