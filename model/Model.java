package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.awt.Image;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Model {
    private File currentDir;
    private File currentFile;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public ImageIcon getPicture(JPanel panel, JFrame frame) {
        try {
            Image image = ImageIO.read(currentFile);
            return new ImageIcon(image.getScaledInstance((panel.getWidth() - frame.getWidth()) + panel.getWidth(),
                    (panel.getHeight() - frame.getHeight()) + panel.getHeight(), Image.SCALE_FAST));
        } catch (IOException e) {
            e.printStackTrace();
            return new ImageIcon();
        }
    }

    public void setCurrentFile(File file) {
        currentFile = file;
    }

    public void setCurrentDir(File file) {
        currentDir = file;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void updatePicture() {
        observers.stream().forEach(o -> o.update());
    }

    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * Observer
     */
    public static interface Observer {
        void update();

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
