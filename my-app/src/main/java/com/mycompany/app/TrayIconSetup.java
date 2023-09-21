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

public class TrayIconSetup {
    SystemTray tray;
    PopupMenu trayPopup;
    WindowFrame frame;
    TrayIcon trayIcon;

    public TrayIconSetup(WindowFrame frame2, String SoonestBinPath) {

        this.frame = frame2;

        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        // System.out.println(today);
        // String soonestBin = selectImage(this.today, dates);
        tray = SystemTray.getSystemTray();
        Image img = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource(SoonestBinPath));
        trayPopup = new PopupMenu("bin day");
        trayIcon = new TrayIcon(img, "bin day", trayPopup);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Tray Icon - Mouse clicked!");
                frame.setVisible(true);
            }
        };

        MenuItem close = new MenuItem("close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopup.add(close);

        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(mouseAdapter);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
    }

    public void getNewData() {
        // data = dataservice.getdata();
        // dates = htmlparser.parse(data);
        // soonestbinpath = soonestbin.selectimage(dates);

        // Image img = Toolkit.getDefaultToolkit()
        // .getImage(getClass().getResource(SoonestBinPath));

        // this.trayIcon.setImage(img);

    }

}
