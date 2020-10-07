package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.acl.Group;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.imageio.*;
import java.awt.image.*;

public class Home extends JPanel {
    Window currentFrame;
    Image image;
    JLabel label = new JLabel();
    JLabel fileName = new JLabel();

    public Home(Window currentFrame) {
        super();
        this.currentFrame = currentFrame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent componentEvent) {
                changeImage(currentFrame.currentFile);

            }

            public void componentHidden(ComponentEvent componentEvent) {

            }

            public void componentMoved(ComponentEvent componentEvent) {

            }

            public void componentShown(ComponentEvent componentEvent) {

            }
        });

        add(fileName);
        add(label);

        JButton prevButton = new JButton();
        prevButton.addActionListener((event) -> {
            currentFrame.currentFile = currentFrame.prevImage();
            changeImage(currentFrame.currentFile);
        });
        prevButton.setText("Prev");

        JButton nextButton = new JButton();
        nextButton.addActionListener((event) -> {
            currentFrame.currentFile = currentFrame.nextImage();
            changeImage(currentFrame.currentFile);
        });
        nextButton.setText("Next");

        add(prevButton);
        add(nextButton);

        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getButton() == MouseEvent.BUTTON1) {
                    currentFrame.currentFile = currentFrame.nextImage();
                    changeImage(currentFrame.currentFile);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    currentFrame.currentFile = currentFrame.prevImage();
                    changeImage(currentFrame.currentFile);
                }

                System.out.println("Mouse Click");

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

    }

    public void changeImage(File file) {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(getWidth(),
                    getHeight() - (getHeight() / 10), Image.SCALE_FAST));
            label.setIcon(icon);

            // fileName.setText("File Name: " + file.getName());
            currentFrame.setTitle("File Name: " + file.getName());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
