package com.mycompany.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class WindowFrame {
    final JFrame frame;
    Rectangle size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    public int height = 200;
    public int width = 350;
    int maxX = size.width - this.width;
    int maxY = size.height - this.height;
    final static String LOOKANDFEEL = "GTK";
    final static String THEME = "Ocean";

    public WindowFrame() {

        this.frame = new JFrame("Upcoming bin days:");
        // this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is
        // collected next").getImage());

        this.frame.setSize(this.width, this.height);
        this.frame.setLocation(this.maxX, this.maxY);
        this.frame.setResizable(false);

        JPanel contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(5, 10, 5, 5);
        contentPanel.setBorder(padding);
        this.frame.setContentPane(contentPanel);

        // initLayout(dates);
    }

    public void Update(HashMap<String, String> dates, String soonestBinPath) {

        this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is collected next").getImage());

        this.frame.setLayout(new GridBagLayout());
        this.frame.getContentPane().setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        String greenBin = (String) dates.get("GreenBin");
        JLabel green = new JLabel(greenBin,
                createImageIcon("/images/greenbinicon.jpg", "green bin image"),
                JLabel.LEFT);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        this.frame.add(green, c);

        String blueBin = (String) dates.get("LargeBlueContainer");
        JLabel blue = new JLabel(blueBin,
                createImageIcon("/images/bluebinicon.jpg", "blue bin image"),
                JLabel.LEFT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        this.frame.add(blue, c);

        String brownBin = (String) dates.get("LargeBrownContainer");
        JLabel brown = new JLabel(brownBin,
                createImageIcon("/images/brownbinicon.jpg", "brown bin image"),
                JLabel.LEFT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        this.frame.add(brown, c);

        String blackBin = (String) dates.get("LargeDomesticWasteContainer");
        JLabel black = new JLabel(blackBin,
                createImageIcon("/images/greybinicon.jpg", "black bin image"),
                JLabel.LEFT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        this.frame.add(black, c);
    }

    protected ImageIcon createImageIcon(String path,
            String description) {
        // java.net.URL imgURL = getClass().getResource(path);
        // if (imgURL != null) {
        // return new ImageIcon(imgURL, description);
        // } else {
        // System.err.println("Couldn't find file: " + path);
        // return null;
        // }

        try (InputStream inputStream = WindowFrame.class.getResourceAsStream(path)) {
            if (inputStream != null) {
                BufferedImage img = ImageIO.read(inputStream);
                return new ImageIcon(img);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setVisible(boolean b) {
        this.frame.setVisible(true);
        System.out.println(this.frame.isVisible());
    }

    public boolean isVisible() {

        return this.frame.isVisible();
    }

}
