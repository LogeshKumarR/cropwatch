package org.cropwatch.service;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class SmsService {
	static String appKey = "6f1111a1-fef3-4a49-8ca2-582760fce262";
	static String pubKey = "b7db79d8-556c-4b1c-9e11-1b7d3bfff2bd";
	
	public void setAppKey(String appKey)
	{
		this.appKey = appKey;
	}
	public void setPubKey(String pubKey)
	{
		this.pubKey = pubKey;
	}
	public static int sendPushMessage(String message, String mobileHash) {
		

		String head = "<html>"
			+"<head>"
			+"<meta name=\"txtweb-appkey\" content=\""+appKey+"\">"
			+"</head>"
			+"<body>";

		String tail = "</body></html>";

		String htmlMessage = head + message + tail; 

		try{
			String urlParams = 	"txtweb-message="+URLEncoder.encode(htmlMessage,"UTF-8")
			+"&txtweb-mobile="+URLEncoder.encode(mobileHash,"UTF-8")
			+"&txtweb-pubkey="+URLEncoder.encode(pubKey,"UTF-8");

			//Using DOM parser to parse the XML response from the API
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			URLConnection conn = new URL("http://api.txtweb.com/v1/push").openConnection();
			conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(urlParams);
		    wr.flush();
		    
			Document doc = db.parse(conn.getInputStream());

			NodeList statusNodes = doc.getElementsByTagName("status");
			String code = "-1";
			for(int index = 0; index < statusNodes.getLength(); index++){
				Node childNode = statusNodes.item(index);
				if( childNode.getNodeType() == Node.ELEMENT_NODE ){
					Element element = (Element) childNode;
					code = getTagValue("code", element);					
					return Integer.parseInt(code);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return -999; //APPLICATION ERROR
	}
	private static String getTagValue(String sTag, Element eElement) {
        NodeList nodeList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
