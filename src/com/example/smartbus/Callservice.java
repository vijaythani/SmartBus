package com.example.smartbus;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class Callservice {

	private static String NAMESPACE="http://Reg/";
	private static String URL="http://192.168.43.166:8080/Train_Service/Train?WSDL";

public static String registerService(String username, String password,String email,String phone_number, String method) {
	// TODO Auto-generated method stub
	String restex=null;
	SoapObject soap=new SoapObject(NAMESPACE, method);
	soap.addProperty("username",username);
	soap.addProperty("password",password);
	soap.addProperty("email",email);
	soap.addProperty("phonenumber",phone_number);
	SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(soap);
	HttpTransportSE http=new HttpTransportSE(URL);
	try {
		http.call(NAMESPACE+method, envelope);
		SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
		restex=primitive.toString();
		
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
		
	} catch (XmlPullParserException e) {
		e.printStackTrace();
		return "error";
		
	}
	
	return restex;

}

public static String bankService(String s1, String s2,String s3,String s4, String method) {
	// TODO Auto-generated method stub
	String restex=null;
	SoapObject soap=new SoapObject(NAMESPACE, method);
	soap.addProperty("name",s1);
	soap.addProperty("accountno",s2);
	soap.addProperty("cvv",s3);
	soap.addProperty("expirydate",s4);
	SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(soap);
	HttpTransportSE http=new HttpTransportSE(URL);
	try {
		http.call(NAMESPACE+method, envelope);
		SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
		restex=primitive.toString();
		
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
		
	} catch (XmlPullParserException e) {
		e.printStackTrace();
		return "error";
		
	}
	
	return restex;

}

public static String loginService(String username, String password,String method) {
	// TODO Auto-generated method stub
	String restex=null;
	SoapObject soap=new SoapObject(NAMESPACE, method);
	soap.addProperty("username",username);
	soap.addProperty("password",password);
	SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(soap);
	HttpTransportSE http=new HttpTransportSE(URL);
	try {
		http.call(NAMESPACE+method, envelope);
		SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
		restex=primitive.toString();
		
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
		
	} catch (XmlPullParserException e) {
		e.printStackTrace();
		return "error";
		
	}
	
	return restex;

}

public static String ResultService(String username, String amount,String method) {
	// TODO Auto-generated method stub
	String restex=null;
	SoapObject soap=new SoapObject(NAMESPACE, method);
	soap.addProperty("username",username);
	soap.addProperty("amount",amount);
	SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(soap);
	HttpTransportSE http=new HttpTransportSE(URL);
	try {
		http.call(NAMESPACE+method, envelope);
		SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
		restex=primitive.toString();
		
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
		
	} catch (XmlPullParserException e) {
		e.printStackTrace();
		return "error";
		
	}
	
	return restex;

}
public static String getDeviceList1(String username,String method) {
	String restex=null;
	SoapObject soap=new SoapObject(NAMESPACE, method);
	soap.addProperty("username",username);
	SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(soap);
	HttpTransportSE http=new HttpTransportSE(URL);
	try {
		http.call(NAMESPACE+method, envelope);
		SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
		restex=primitive.toString();
		
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
		
	} catch (XmlPullParserException e) {
		e.printStackTrace();
		return "error";
		
	}
	
	return restex;
}
public static String PaymentService(String username,String method) {
	String restex=null;
	SoapObject soap=new SoapObject(NAMESPACE, method);
	soap.addProperty("username",username);
	SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	envelope.setOutputSoapObject(soap);
	HttpTransportSE http=new HttpTransportSE(URL);
	try {
		http.call(NAMESPACE+method, envelope);
		SoapPrimitive primitive =(SoapPrimitive) envelope.getResponse();
		restex=primitive.toString();
		
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
		
	} catch (XmlPullParserException e) {
		e.printStackTrace();
		return "error";
		
	}
	
	return restex;
}

}

