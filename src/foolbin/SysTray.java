package foolbin;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/* This file borrows heavily from Oracle tutorials see the copyright notice at the end */
/* Thank you to gcons for the icon! Go to http://greepit.com for more info */

public class SysTray {
	
	private PopupMenu popup;
	private TrayIcon trayIcon;
	private SystemTray tray;
	private Settings settings;
	private SettingsGUI gui;

	public SysTray(final Settings settings, final SettingsGUI gui){
		this.gui = gui;
        this.settings=settings;

	    Image icon=createImage("images/satellite.png", "tray icon");
	    if (icon == null) {
	    	URL imgURL = ClassLoader.getSystemResource("satellite.png");
	    	icon = Toolkit.getDefaultToolkit().getImage(imgURL);
	    }
	    gui.setIcon(icon);
	    
	    
		//Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.err.println("SystemTray is not supported");
            JOptionPane.showMessageDialog(null, "I can't bring up the system tray :-(");
            gui.showGui();
            return;
        }
	    popup = new PopupMenu();
	    trayIcon = new TrayIcon(icon);
	    tray = SystemTray.getSystemTray();
	    
	    CheckboxMenuItem CBAutoIP = new CheckboxMenuItem("Auto-detect IP");
	    MenuItem MSettings = new MenuItem("Settings");
	    MenuItem Quit = new MenuItem("Quit");
	    
	    popup.add(CBAutoIP);
	    popup.add(MSettings);
	    popup.addSeparator();
	    popup.add(Quit);
	    
	    trayIcon.setToolTip("Sitelutiosn DDNS Client: Initialized");
	    trayIcon.setPopupMenu(popup);
	    
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
        CBAutoIP.setState(true);
        CBAutoIP.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED){
                    settings.setAutoIP(true);
                } else {
                    settings.setAutoIP(false);
                }
            }
        });
        
        MSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.toggleGui();
            }
        });
        
        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	settings.save("slfoolbin.settings");
            	System.exit(0);
            }
        });
	    
	}
	
	public Image getIconImg(){
		return trayIcon.getImage();
	}
	
	public void setToolTip(String tip){
		//This may be called even if we couldn't start the systray
		if (trayIcon != null){
			trayIcon.setToolTip(tip);
		}
	}
	
    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = SysTray.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

}



/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */