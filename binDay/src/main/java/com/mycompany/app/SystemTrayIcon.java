package com.mycompany.app;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

        trayPopup = new PopupMenu();
        MenuItem close = new MenuItem("close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopup.add(close);

        tray = SystemTray.getSystemTray();
        Image img = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource(soonestBin.getSoonestBin()));
        trayIcon = new TrayIcon(img, "bin day", trayPopup);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // only set visible on left mouse click
                if (e.getButton() == 1) {
                    frame.setVisible(true);
                    CompletableFuture.delayedExecutor(5, TimeUnit.MILLISECONDS).execute(() -> {
                        try {
                            getNewData(frame2, soonestBin, updater);
                        } catch (AWTException e1) {
                            e1.printStackTrace();
                        }
                    });
                }

            }
        };

        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(mouseAdapter);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
    }

    public void getNewData(WindowFrame frame, SoonestBin soonestBin, Updater updater) throws AWTException {

        updater.Update();
        String soonestBinPath = soonestBin.getSoonestBin();

        System.out.println("after set soonest bin");

        Image img = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource(soonestBinPath));

        try {
            this.trayIcon.setImage(img);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
