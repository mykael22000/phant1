/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.mik.phant1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jmap.JConv;

/**
 *
 * @author Michael
 */
public class Phant1 {
    
    static String cluster = "mik-phant";
    static String index = "phant-alert1";  //Must be lower case
    static String type = "alpha";
    
    public static void main(String[] args) { 
        
        // Get some JSON data to send
        
        Map<String, Object> json = new HashMap<>();
        json.put("source","Phant1 Alpha Testing");
        json.put("timestamp",new Date().getTime());
        json.put("text","Test Heffalump alert for Phant1");
        json.put("severity","Critical");
        json.put("source_link","https://github.com/mykael22000/phant1");        
        json.put("class","mik.phant/TEST1");
        json.put("impacts","[\"host/Miks_desktop\",\"dev/Master\"]");  
        json.put("data","[{}]");        
        json.put("tokens","[\"Miks_desktop\",\"93%\"]");        
        System.out.println(json);
        
        JConv jc = new JConv();
        
        byte[] jstring;
        jstring = jc.JSONify(json);
        String dump = new String(jstring);
        System.out.println(dump);
        
        // Go index it
        
        Indexer ix = new Indexer(cluster, index, type);
        
        ix.indexAlert(jstring);
        
        return;
    }
          

}
