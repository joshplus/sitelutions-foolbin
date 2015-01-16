package foolbin;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws UnsupportedEncodingException{
		Requester r = new Requester();
		System.out.println("Hi!");
		
		Settings s = new Settings("slfoolbin.settings");
		s.setRefreshInterval(30);
		//s.load("~/slfoolbin.settings");
		SettingsGUI sgui = new SettingsGUI(s);
		SysTray tray = new SysTray(s,sgui);
		sgui.showGui();
		String result="";
		while (true){
			String updateURL = "https://www.sitelutions.com/dnsup?user="+URLEncoder.encode(s.getUsername(), "UTF-8");
			updateURL += "&pass="+URLEncoder.encode(s.getPassword(),"UTF-8");
			updateURL += "&id="+URLEncoder.encode(s.getIDString(), "UTF-8");
			if (s.getAutoIP()) {
				updateURL+="&detectip=1";
			} else {
				try {
					updateURL+="&ip="+InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					//If we can't figure out our own IP, fail back to auto detection
					updateURL+="&detectip=1";
					System.out.println("I couldn't figure out my own IP :-(");
				}
			}
			updateURL += "&ttl="+s.getTTL();
			//System.out.println(updateURL);
			result = Requester.getBody(updateURL);
			tray.setToolTip(result);
			System.out.println("Return code: " + result);
			try {
				Thread.sleep(100+(s.getRefreshInterval()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
