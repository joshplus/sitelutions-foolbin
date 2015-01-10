package foolbin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class Settings {
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
		         System.out.printf("Serialized data is saved in /tmp/employee.ser");
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
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c)
		      {
		         System.out.println("Employee class not found");
		         c.printStackTrace();
		         return;
		      }
		}
	
	public void setAutoIP(Boolean AutoIP){
		this.AutoIP=AutoIP;
	}
	
	public void setRefreshInterval(int interval){
		interval=this.interval;
	}
	
	public void setRefreshInterval(String sinterval){
		setRefreshInterval(Integer.parseInt(sinterval));
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
		setTTL(Integer.parseInt(sttl));
	}

	public int getRefreshInterval(){
		return interval;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
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
			if (!start){
				idlist+=",";
			} else {
				start=true;
			}
			idlist+=id;
		}
		return idlist;
	}
}
