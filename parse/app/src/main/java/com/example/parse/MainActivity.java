package com.example.parse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    TextView rexml, rejson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rexml=findViewById(R.id.resxml);
        rejson=findViewById(R.id.resjson);

    }
    public void XMLParser(View v){
        try{
            InputStream is = getAssets().open("city.xml");
            DocumentBuilderFactory dbFactory =DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element= doc.getDocumentElement();
            element.normalize();
            NodeList nList= doc.getElementsByTagName("place");
            rexml.setText("XML DATA");
            for(int i=0;i<nList.getLength();i++){
                Node node =nList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element2 =(Element)node;
                    rexml.setText(rexml.getText()+"\n City Name:"+getValue("cityname",element2)+"\n");
                    rexml.setText(rexml.getText()+"\n Lat:"+getValue("lat",element2)+"\n");
                    rexml.setText(rexml.getText()+"\n Long:"+getValue("long",element2)+"\n");
                    rexml.setText(rexml.getText()+"\n Temperature:"+getValue("temp",element2)+"\n");
                    rexml.setText(rexml.getText()+"\n Humidity:"+getValue("humidity",element2)+"\n");
                    rexml.setText(rexml.getText()+"---------------------------");
                }
            }

        }catch(IOException | ParserConfigurationException| SAXException e){
            e.printStackTrace();
        }
    }
    private static String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node=nodeList.item(0);
        return node.getNodeValue();
    }
     public void JSONParser(View v){
        String json;
       try{
           InputStream is = getAssets().open("city1.json");
           int size = is.available();
           byte[] buffer = new byte[size];
           is.read(buffer);
           is.close();
           json = new String(buffer, "UTF-8");
           JSONArray jsonArray = new JSONArray(json);
           rejson.setText("JSON DATA");
           for(int i=0;i<jsonArray.length();i++){
               JSONObject obj=jsonArray.getJSONObject(i);
               rejson.setText(rejson.getText()+"\n City Name:"+obj.getString("name")+"\n");
               rejson.setText(rejson.getText()+"\n Lat:"+obj.getString("lat")+"\n");
               rejson.setText(rejson.getText()+"\n Long:"+obj.getString("long")+"\n");
               rejson.setText(rejson.getText()+"\n Temperature:"+obj.getString("temp")+"\n");
               rejson.setText(rejson.getText()+"\n Humidity:"+obj.getString("humidity")+"\n");
               rejson.setText(rejson.getText()+"\n --------------------");
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       } catch (JSONException e) {
           throw new RuntimeException(e);
       }

     }
}