package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import model.Model;

public class Controller {
    public static ActionListener onOpenFileChooserListener(final Model model, final JFileChooser chooser) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = chooser.showOpenDialog(chooser.getRootPane());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    model.setCurrentDir(chooser.getCurrentDirectory());
                    model.setCurrentFile(chooser.getSelectedFile());
                    model.updatePicture();
                }

            }

        };
    }

    public static ActionListener onNextImage(final Model model) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurrentFile(model.nextImage());
                model.updatePicture();
            }

        };
    }

    public static ActionListener onPrevImage(final Model model) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.setCurrentFile(model.prevImage());
                model.updatePicture();
            }

        };
    }

    public static ComponentListener onScreenResize(final Model model) {
        return new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                model.updatePicture();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void componentShown(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // TODO Auto-generated method stub

            }

        };
    }

    public static ActionListener onClose(final JFrame frame) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }

        };
    }

    public static ActionListener onToggleHelp(final JDialog dialog) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(!dialog.isShowing());
            }

        };
    }

    public static MouseListener onClick(final Model model) {
        return new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getButton() == MouseEvent.BUTTON1) {
                    model.setCurrentFile(model.nextImage());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    model.setCurrentFile(model.prevImage());
                }
                model.updatePicture();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        };
    }

}
