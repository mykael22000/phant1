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
    static String index = "phant-test1";  //Must be lower case
    static String type = "test1";
    
    public static void main(String[] args) { 
        
        // Get some JSON data to send
        
        Map<String, Object> json = new HashMap<>();
        json.put("user","kimchy");
        json.put("postDate",new Date().getTime());
        json.put("message","trying out Elasticsearch");
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
