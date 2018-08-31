/**
 * 
 */
package com.example.smartbus;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Admin
 *
 */
public class CalculateDistance {
	 
	public String getDistanceFromJson(double lat1,double lon1,double lat2,double lon2) {	
		String  distance = "",text="";
		StringBuffer jsonstring = new StringBuffer();
		HttpClient client = new DefaultHttpClient();		
		//String url="https://maps.googleapis.com/maps/api/directions/json?origin=13.123456,80.321654&destination=12.922915,80.127456&sensor=false&mode=driving";
		String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+lat1+","+lon1+"&destination="+lat2+","+lon2+"&sensor=false&mode=driving";
		try {
			
	//		HttpGet httpRequest = new HttpGet(new URL(url).toURI());
	//		HttpPost httpPost = new HttpPost(url);
			System.out.println("^^^^^^^^^^^^"+url);
    //		HttpResponse response = (HttpResponse)client.execute(httpPost);
    //		HttpEntity entity = response.getEntity();
			HttpGet httpRequest = new HttpGet(new URL(url).toURI());
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) httpClient.execute(httpRequest);				
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			int ch=0;
			while((ch=is.read())!=-1) {
				jsonstring.append((char) ch);
			}
			is.close();
			JSONObject json = new JSONObject(jsonstring.toString());			
			JSONArray jsonarray = json.getJSONArray("routes");
			JSONObject jsonobject =jsonarray.getJSONObject(0);
			JSONObject jso=jsonobject.getJSONArray("legs").getJSONObject(0);			
			for(int i=0;i<jsonarray.length();i++) {				
				JSONObject j = new JSONObject(jso.getString("distance"));
				text=j.getString("text");
				//distance = jsonobject.getString("bounds");				
			}
		} catch (ClientProtocolException e) {	
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {	
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {	
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (URISyntaxException e) {			
			e.printStackTrace();
		}
		System.out.println("#### "+text);
		return text+">>>>"+distance;
	}
	
	/**
	 * calculate distance from google xml result
	 */
	public static String getDistance(double lat1, double lon1, double lat2, double lon2) {
		String result_in_kms = "";
		String url = "http://maps.google.com/maps/api/directions/xml?origin=" + lat1 + "," + lon1 + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
		String tag[] = {"text"};
		HttpResponse response = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpPost httpPost = new HttpPost(url);
			response = httpClient.execute(httpPost, localContext);
			InputStream is = response.getEntity().getContent();
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			if (doc != null) {
				NodeList nl;
				ArrayList args = new ArrayList();
				for (String s : tag) {
					nl = doc.getElementsByTagName(s);
					if (nl.getLength() > 0) {
						Node node = nl.item(nl.getLength() - 1);
						args.add(node.getTextContent());
					} else {
						args.add(" - ");
					}
				}
				result_in_kms = String.format("%s", args.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_in_kms;
	}

}
