package foolbin;

import java.util.HashSet;
import java.util.Set;

public class Settings {
	private Boolean AutoIP;
	private int interval, ttl;
	private String username;
	private String password;
	private Set<Integer> ids;
	
	public Settings(){
		ids=new HashSet<Integer>();
		AutoIP=true;
	}
	
	public void setAutoIP(Boolean AutoIP){
		this.AutoIP=AutoIP;
	}
	
	public void setRefreshInterval(int interval){
		interval=this.interval;
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
		return (Integer[])ids.toArray();
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
