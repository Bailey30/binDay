package com.mycompany.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseEvent;

public class WindowFrame {
    final JFrame frame;
    Rectangle size = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    public int height = 200;
    public int width = 370;
    int maxX = size.width - this.width;
    int maxY = size.height - this.height;
    final static String LOOKANDFEEL = "GTK";
    final static String THEME = "Ocean";
    String greenBin;
    JLabel green;
    JLabel blue;
    JLabel brown;
    JLabel black;
    JPanel contentPanel = new JPanel();
    JPanel loadingPanel;
    int mouseX;
    int mouseY;
    Color gray = new Color(192, 192, 192);
    Font font = new Font("MS Sans Serif", Font.BOLD, 15);
    Font binFont = new Font("MS Sans Serif", Font.BOLD, 13);

    public WindowFrame() {

        this.frame = new JFrame("Upcoming bin days:");
        // this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is
        // collected next").getImage());

        this.frame.setSize(this.width, this.height);
        this.frame.setLocation(this.maxX, this.maxY);
        this.frame.setResizable(false);

        Border padding = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        this.contentPanel.setBorder(padding);

        this.frame.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 0, 1,
                1, Color.BLACK));

        contentPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.DARK_GRAY));

        this.frame.setContentPane(contentPanel);

        FrameDragListener frameDragListener = new FrameDragListener(frame);

        this.frame.addMouseListener(frameDragListener);
        this.frame.addMouseMotionListener(frameDragListener);
        this.frame.setUndecorated(true);
        this.frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        // initLayout(dates);
    }

    public void Init(HashMap<String, String> dates, String soonestBinPath) {

        System.out.println(dates);

        // does nothing as frame is undecorated
        this.frame.setIconImage(createImageIcon(soonestBinPath, "the bin that is collected next").getImage());

        this.frame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(2, 2, 2, 2);

        // add top bar with close button

        JPanel topBar = initTopBar(c, soonestBinPath);

        this.frame.add(topBar, c);

        // create bin date labels

        // green bin

        greenBin = (String) dates.get("GreenBin");
        green = new JLabel(greenBin,
                createImageIcon("/images/greenbinicon-modified.png", "green bin image"),
                JLabel.LEFT);
        green.setFont(this.binFont);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        green.setBorder(new EmptyBorder(0, 7, 0, 0));
        this.frame.add(green, c);

        // blue bin

        String blueBin = (String) dates.get("LargeBlueContainer");
        blue = new JLabel(blueBin,
                createImageIcon("/images/bluebinicon-modified.png", "blue bin image"),
                JLabel.LEFT);
        blue.setFont(this.binFont);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        blue.setBorder(new EmptyBorder(0, 10, 0, 0));
        this.frame.add(blue, c);

        // brown bin

        String brownBin = (String) dates.get("LargeBrownContainer");
        brown = new JLabel(brownBin,
                createImageIcon("/images/brownbinicon-modified.png", "brown bin image"),
                JLabel.LEFT);
        brown.setFont(this.binFont);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        brown.setBorder(new EmptyBorder(0, 7, 0, 0));
        this.frame.add(brown, c);

        // black bin

        String blackBin = (String) dates.get("LargeDomesticWasteContainer");
        black = new JLabel(blackBin,
                createImageIcon("/images/greybinicon-modified.png", "black bin image"),
                JLabel.LEFT);
        black.setFont(this.binFont);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        black.setBorder(new EmptyBorder(0, 10, 0, 0));
        this.frame.add(black, c);

        // create loading icon

        this.loadingPanel = new JPanel();
        this.loadingPanel.setBounds(this.width / 2 - 50, 35, 100, 20); // set position
        this.loadingPanel.setBackground(Color.LIGHT_GRAY);
        this.loadingPanel.setLayout(null);

        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setBounds(0, 0, 100, 20); // set label bounds
        loadingLabel.setFont(this.binFont);
        this.loadingPanel.add(loadingLabel);

        frame.getLayeredPane().add(this.loadingPanel, JLayeredPane.POPUP_LAYER);

        // hide loading icon on init

        this.loadingPanel.setVisible(false);

        System.out.println("end of init");
    }

    public JPanel initTopBar(GridBagConstraints c, String soonestBinPath) {
        JPanel topBar = new JPanel();

        topBar.setLayout(new BoxLayout(topBar, BoxLayout.LINE_AXIS));
        topBar.setBackground(new Color(0, 0, 128));
        topBar.setPreferredSize(new Dimension(width, 10));
        topBar.setBorder(new EmptyBorder(2, 5, 2, 2));

        JLabel titleLabel = new JLabel("Upcoming bin days:");
        titleLabel.setFont(this.font);
        titleLabel.setForeground(new Color(237, 240, 245));

        topBar.add(titleLabel);
        topBar.add(Box.createHorizontalStrut(198)); // Add glue to push the button to

        JButton closeButton = new JButton("<html><div style='margin-right: 3px; margin-bottom: 2px'>✖</div></html>");
        closeButton.setFont(font);
        closeButton.setMinimumSize(new Dimension(20, 20));
        closeButton.setMaximumSize(new Dimension(20, 20));
        closeButton.setVerticalAlignment(SwingConstants.CENTER);
        closeButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE,
                Color.DARK_GRAY));
        closeButton.setBackground(gray);
        closeButton.addActionListener(e -> {
            this.frame.setVisible(false);
        });

        topBar.add(closeButton);

        c.weighty = 0.4;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;

        return topBar;
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
