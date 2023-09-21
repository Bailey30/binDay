package com.mycompany.app;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class SystemTrayIcon {

    SystemTray tray;
    PopupMenu trayPopup;
    WindowFrame frame;
    TrayIcon trayIcon;

    public SystemTrayIcon(WindowFrame frame2, SoonestBin soonestBin, Updater updater) {

        this.frame = frame2;

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        tray = SystemTray.getSystemTray();
        Image img = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource(soonestBin.getSoonestBin()));
        trayIcon = new TrayIcon(img, "bin day", trayPopup);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.setVisible(true);
                getNewData(frame2, soonestBin, updater);
            }
        };

        MenuItem close = new MenuItem("close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(mouseAdapter);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
    }

    public void getNewData(WindowFrame frame, SoonestBin soonestBin, Updater updater) {

        updater.Update();
        String soonestBinPath = soonestBin.getSoonestBin();

        System.out.println("after set soonest bin");

        Image img = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource(soonestBinPath));

        trayIcon = new TrayIcon(img, "bin day");

        this.trayIcon.setImage(img);

    }

}
