package com.mycompany.app;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;;

public class WindowFrame {
    final JFrame frame;
    Rectangle size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    public int height = 200;
    public int width = 350;
    int maxX = size.width - this.width;
    int maxY = size.height - this.height;
    final static String LOOKANDFEEL = "GTK";
    final static String THEME = "Ocean";
    String greenBin;
    JLabel green;
    JLabel blue;
    JLabel brown;
    JLabel black;
    JPanel contentPanel;
    JPanel loadingPanel;

    public WindowFrame() {

        this.frame = new JFrame("Upcoming bin days:");
        // this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is
        // collected next").getImage());

        this.frame.setSize(this.width, this.height);
        this.frame.setLocation(this.maxX, this.maxY);
        this.frame.setResizable(false);

        contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(5, 10, 5, 5);
        contentPanel.setBorder(padding);

        this.frame.setContentPane(contentPanel);

        // initLayout(dates);
    }

    public void Init(HashMap<String, String> dates, String soonestBinPath) {

        System.out.println(dates);

        this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is collected next").getImage());

        this.frame.setLayout(new GridBagLayout());
        this.frame.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();

        greenBin = (String) dates.get("GreenBin");
        green = new JLabel(greenBin,
                createImageIcon("/images/greenbinicon.jpg", "green bin image"),
                JLabel.LEFT);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        this.frame.add(green, c);

        String blueBin = (String) dates.get("LargeBlueContainer");
        blue = new JLabel(blueBin,
                createImageIcon("/images/bluebinicon.jpg", "blue bin image"),
                JLabel.LEFT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        this.frame.add(blue, c);

        String brownBin = (String) dates.get("LargeBrownContainer");
        brown = new JLabel(brownBin,
                createImageIcon("/images/brownbinicon.jpg", "brown bin image"),
                JLabel.LEFT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        this.frame.add(brown, c);

        String blackBin = (String) dates.get("LargeDomesticWasteContainer");
        black = new JLabel(blackBin,
                createImageIcon("/images/greybinicon.jpg", "black bin image"),
                JLabel.LEFT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        this.frame.add(black, c);

        // create loading icon

        this.loadingPanel = new JPanel();
        this.loadingPanel.setBounds(this.width / 2 - 50, 0, 100, 20); // set position
        this.loadingPanel.setBackground(Color.WHITE);
        this.loadingPanel.setLayout(null);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setBounds(0, 0, 100, 20); // set label bounds
        this.loadingPanel.add(loadingLabel);

        frame.getLayeredPane().add(this.loadingPanel, JLayeredPane.POPUP_LAYER);

        // hide loading icon on init

        this.loadingPanel.setVisible(false);

        System.out.println("end of init");
    }

    public void initLoader() {
        this.loadingPanel = new JPanel();
        this.loadingPanel.setBounds(0, 0, this.width, 20); // Set the position and size of the panel
        this.loadingPanel.setOpaque(true); // Make sure it's opaque
        this.loadingPanel.setBackground(Color.WHITE);
        this.loadingPanel.setLayout(null);

        JLabel loaderLabel = new JLabel("updating...");
        loaderLabel.setBounds(0, 0, this.width, 20);
        loaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loaderLabel.setVerticalAlignment(SwingConstants.TOP);
        this.loadingPanel.add(loaderLabel);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(null); // Use null layout for absolute positioning
        contentPane.add(this.loadingPanel);
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
    }

    public boolean isVisible() {

        return this.frame.isVisible();
    }

    public void Update(HashMap<String, String> dates, String soonestBinPath) {

        green.setText(dates.get("GreenBin"));
        blue.setText(dates.get("LargeBlueContainer"));
        brown.setText(dates.get("LargeBrownContainer"));
        black.setText(dates.get("LargeDomesticWasteContainer"));

        this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is collected next").getImage());

    }

    public void addLoader() {
        System.out.println("adding loader");
        this.loadingPanel.setVisible(true);

    }

    public void removeLoader() {
        System.out.println("removing loader");
        this.loadingPanel.setVisible(false);
    }

}
