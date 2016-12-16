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
import java.awt.event.ComponentAdapter;

import static com.sun.glass.ui.Cursor.setVisible;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        saveGameDateLabel.setText(FileOperator.getGameSaveDate());
        backupSaveGameDateLabel.setText(FileOperator.getBackupSaveDate());
        Color defaultBgColor = rootPanel.getBackground();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOperator.saveGame();
                backupSaveGameDateLabel.setText(FileOperator.getBackupSaveDate());

                rootPanel.setBackground(defaultBgColor);
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOperator.loadGame();
                rootPanel.setBackground(defaultBgColor);
            }
        });

        Timer SimpleTimer = new Timer(500, new ActionListener() {
            String lastDate = FileOperator.getGameSaveDate();
            @Override
            public void actionPerformed(ActionEvent e) {

                saveGameDateLabel.setText(FileOperator.getGameSaveDate());
                if (!lastDate.equals(saveGameDateLabel.getText())) {
                    lastDate=saveGameDateLabel.getText();
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
//todo
    private void playSound() {
        Toolkit.getDefaultToolkit().beep();
    }

//    public static synchronized void playSound(final String url) {
//        new Thread(new Runnable() {
//            // The wrapper thread is unnecessary, unless it blocks on the
//            // Clip finishing; see comments.
//            public void run() {
//                try {
//                    Clip clip = AudioSystem.getClip();
//                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
//                            Main.class.getResourceAsStream("" + url));
//                    clip.open(inputStream);
//                    clip.start();
//                } catch (Exception e) {
//                    System.err.println(e.getMessage());
//                }
//            }
//        }).start();
//    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
