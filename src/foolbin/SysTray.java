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
	
	public SysTray(){
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.err.println("SystemTray is not supported");
            return;
        }
		
	    popup = new PopupMenu();
	    trayIcon = new TrayIcon(createImage("images/satelite.gif", "tray icon"));
	    tray = SystemTray.getSystemTray();
	    
	    CheckboxMenuItem CBAutoIP = new CheckboxMenuItem("Auto-detect IP");
	    MenuItem MSettings = new MenuItem("Settings");
	    
	    popup.add(CBAutoIP);
	    popup.add(MSettings);
	    
	    trayIcon.setToolTip("Sitelutiosn DDNS Client: Initialized");
	    
	}
	
	public void setToolTip(String tip){
		trayIcon.setToolTip(tip);
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