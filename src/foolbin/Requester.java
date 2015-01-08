package foolbin;
import java.io.*;
import java.net.*;

public class Requester {

	   public String getBody(String urlToRead) {
		      URL url;
		      HttpURLConnection conn;
		      BufferedReader rd;
		      String line;
		      String result = "";
		      try {
		         url = new URL(urlToRead);
		         conn = (HttpURLConnection) url.openConnection();
		         conn.setRequestMethod("GET");
		         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		         while ((line = rd.readLine()) != null) {
		            result += line;
		         }
		         rd.close();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		      return result;
		   }
}
