package webcrawl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/*This class contains a static function which will fetch the webpage
of the given url and return as a string */
//Get the whole HTML code in current web page
public class SimpleWeb{
	public static String getWeb(String address)throws Exception{
		String webpage = "";
		String inputLine = "";
		URL url = new URL(address);
		
		/*
		 * For top efficiency, 
		 * consider wrapping an InputStreamReader within a BufferedReader
		 */
		BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream(),"utf-8"));
		while ((inputLine = in.readLine()) != null)
			webpage += inputLine;
		in.close();
		
		return webpage;
	}
}
