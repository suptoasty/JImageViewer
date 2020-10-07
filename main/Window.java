package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.Home;

public class Window extends JFrame {
    JFrame currentFrame; // reference for calling methods under child action listeners
    JMenuBar menuBar = new JMenuBar();
    JFileChooser chooser = new JFileChooser();
    File currentDir; // current directory other image are stored
    public File currentFile; // current image file

    public Window() {
        super();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new Home(this));

    }

    public Window(int x, int y) {
        super();

        // settings for window
        currentFrame = this;
        setSize(x, y);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // home page
        Home home = new Home(this);
        getContentPane().add(home);

        // menu bar
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // file chooser dialogue
        JMenuItem open = new JMenuItem("Open Image");
        fileMenu.add(open);
        open.addActionListener((event) -> {
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentDir = chooser.getCurrentDirectory();
                currentFile = chooser.getSelectedFile();
                home.changeImage(currentFile);
            }

        });
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.VK_CONTROL));

        // next image menu item and shortcut
        JMenuItem next = new JMenuItem("Next Image");
        fileMenu.add(next);
        next.addActionListener((event) -> {
            // change image on screen
            currentFile = nextImage();
            home.changeImage(currentFile);
        });
        next.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_CONTROL));

        // prev image menu item and shortcut
        JMenuItem prev = new JMenuItem("Previous Image");
        fileMenu.add(prev);
        prev.addActionListener((event) -> {
            // change image on screen
            currentFile = prevImage();
            home.changeImage(currentFile);
        });
        prev.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_CONTROL));

        // close menu item
        JMenuItem close = new JMenuItem("Close");
        fileMenu.add(close);
        close.addActionListener((event) -> {
            currentFrame.dispose();
        });
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.VK_CONTROL));
    }

    // gets next image
    public File nextImage() {
        File[] files = currentDir.listFiles();

        // regex used to find image files
        String regex = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$";
        Pattern pattern = Pattern.compile(regex);

        // filter so only image files are in array
        files = Arrays.stream(files).filter((newFile) -> pattern.matches(regex, newFile.getName()))
                .toArray(File[]::new);

        // get previous file
        int index = Arrays.asList(files).indexOf(currentFile);
        return files[(index + 1) % files.length];
    }

    // gets prev image
    public File prevImage() {
        File[] files = currentDir.listFiles();

        // regex used to find image files
        String regex = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$";
        Pattern pattern = Pattern.compile(regex);

        // filter so only image files are in array
        files = Arrays.stream(files).filter((newFile) -> pattern.matches(regex, newFile.getName()))
                .toArray(File[]::new);

        // get previous file
        int index = Arrays.asList(files).indexOf(currentFile);
        return files[(index - 1) % files.length];
    }

}
