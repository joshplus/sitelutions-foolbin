package foolbin;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args){
		Requester r = new Requester();
		System.out.println("Hi!");
		
		Settings s = new Settings("~/foolbin.settings");
		SettingsGUI sgui = new SettingsGUI(s);
		SysTray tray = new SysTray(s,sgui);
		sgui.showGui();
		
		for (int i=0; i<10; i++){
			System.out.println(r.getBody("http://vm.verajosh.com/in.php?max="+i+"&min="+i));
		}
		
		while (true){
			try {
				Thread.sleep(s.getRefreshInterval());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String updateURL = "https://www.sitelutions.com/dnsup?user="+s.getUsername();
			updateURL += "&pass="+s.getPassword();
			updateURL += "&id="+s.getIDString();
			if (s.getAutoIP()) {
				updateURL+="&detectip=1";
			} else {
				try {
					updateURL+="&ip="+InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					//If we can't figure out our own IP, fail back to auto detection
					updateURL+="&detectip=1";
				}
			}
			updateURL += "&ttl="+s.getTTL();
		}
	}

}
