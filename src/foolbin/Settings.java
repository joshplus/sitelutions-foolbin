package foolbin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Settings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean AutoIP;
	private int interval, ttl;
	private String username;
	private String password;
	private Set<Integer> ids;
	private String path;
	
	public Settings(String path){
		ids=new HashSet<Integer>();
		AutoIP=true;
		this.path = path;
	}
	
	public void save(String path){
	      try
		      {
		         FileOutputStream fileOut =
		         new FileOutputStream(path);
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(this);
		         out.close();
		         fileOut.close();
		         System.out.println("Serialized config data is saved in " + path);
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
	    }

		public void load(String path){
		      try
		      {
		         FileInputStream fileIn = new FileInputStream(path);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         Settings s = (Settings)in.readObject();
		         in.close();
		         fileIn.close();
		         AutoIP=s.AutoIP;
		         ttl=s.ttl;
		         interval=s.interval;
		         username=s.username;
		         password=s.password;
		         ids=s.ids;
		         System.out.println("Serialized config data loaded from " + path);
		         System.out.println(s);
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c)
		      {
		         System.out.println("WHATLOL");
		         c.printStackTrace();
		         return;
		      }
		}
	
	public void setAutoIP(Boolean AutoIP){
		this.AutoIP=AutoIP;
	}
	
	public void setRefreshInterval(int interval){
		this.interval=interval;
	}
	
	public void setRefreshInterval(String sinterval){
		try {
			setRefreshInterval(Integer.parseInt(sinterval));
		} catch (Exception e) {
			System.err.println(sinterval + " is not an int!");
		}
	}

	public void setUsername(String username){
		this.username=username;
	}

	public void setPassword(String password){
		this.password=password;
	}
	
	public void setTTL(int ttl){
		this.ttl = ttl;
	}
	
	public void setTTL(String sttl){
		try{
			setTTL(Integer.parseInt(sttl));
		} catch (Exception e) {
			System.err.println(sttl + " is not an int!");
		}
	}

	public int getRefreshInterval(){
		return interval;
	}
	
	public String getUsername(){
		if (username==null) return "";
		return username;
	}
	
	public String getPassword(){
		if (password==null) return "";
		return password;
	}
	
	public Boolean getAutoIP(){
		return AutoIP;
	}
	
	public void addID(int id){
		ids.add(id);
	}
	
	public void removeID(int id){
		ids.remove(id);
	}
	
	public Boolean hasID(int id){
		return ids.contains(id);
	}
	
	public Integer[] getIDs(){
		Integer [] arr = new Integer[ids.size()];
		int i=0;
		for (Integer id: ids){
			arr[i++]=id;
		}
		return arr;
	}
	
	public int getTTL(){
		return this.ttl;
	}
	
	public String getIDString(){
		Boolean start=true;
		String idlist="";
		for (Integer id: ids){
			if (start){
				start=false;
			} else {
				idlist+=",";
			}
			idlist+=id;
		}
		return idlist;
	}
	
	public String toString(){
		String strid="["+getIDString()+"]";
		String str = "Settings \nUser:"+username+"\npass:"+password 
				+ "\ninterval:"+interval + "\nttal:"+ttl+"IDs: "+strid;
		
		return str;
	}
}
