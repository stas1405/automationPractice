package main.java.com.RESTAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonBlob extends BaseAPIClass{
	
public static String sendPost() throws Exception {
		
        URL url = new URL(query);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        //conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        String createdPost = conn.getHeaderField("Location");
        //System.out.println(conn.getHeaderField("Location"));
        System.out.println("Record " + json + " created succesfully " + "\n" + "Response code : " + conn.getResponseCode());
        os.close();
        return createdPost;
        
        
	}

/*private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }*/

/*  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } 
    finally {

      is.close();
      
    }
  }*/

  public static String sendGet(String url) throws Exception, IOException, JSONException {
    try {
      InputStream is = new URL(url).openStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      
      StringBuilder sb = new StringBuilder();
      int cp;
      while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
      }
      String jsonText = sb.toString();
      JSONObject json = new JSONObject(jsonText);
      System.out.println("Getting record :" + json.toString());
      return "200";
      
    } catch(Exception  e){
    	System.out.println("Trying to GET an empty record");
    	System.out.println("Response code : 404");
    	return "404";
    }
    finally {

      //is.close();
      
    }

    //System.out.println(json.toString());
    //System.out.println(json.get("id"));
  }

public static int sendDelete(String result) throws IOException {
	URL url = new URL(result);
	HttpsURLConnection httpCon = (HttpsURLConnection) url.openConnection();
	httpCon.setDoOutput(true);
	httpCon.setRequestProperty(
	    "Content-Type", "application/x-www-form-urlencoded" );
	httpCon.setRequestMethod("DELETE");
	
	httpCon.connect();
	System.out.println("Record " + json + " : deleted succesfully"+ " \n"+ "Response code : " + httpCon.getResponseCode());
	
	return httpCon.getResponseCode();
	
}

}
