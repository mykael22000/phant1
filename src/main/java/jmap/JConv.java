/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class JConv{
    
    public byte[] JSONify( Map<String,Object> map ) {
        
        byte[] response = new byte[1];
        
        Object value;
        
        byte[] work;
        
        String quote = "\"";
        String colon = ":";
        String comma = ",";
        String open = "{";
        String close = "}";
        String open_array = "[";
        String close_array = "]";        
        
        int lines = 0;
        
        try {
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
        
            out.write(open.getBytes());
            
            for ( String key : map.keySet() ) {
                if (lines > 0) {
                    out.write(comma.getBytes());
                }
                System.out.println(key + " -> " + map.get(key));
                value = map.get(key);
                work = value.toString().getBytes();
                key = quote + key + quote + colon;
                if (work[0] != '[') { 
                    key += quote; 
                }
                out.write(key.getBytes());
                out.write(work);
                if (work[0] != '[') { 
                    out.write(quote.getBytes()); 
                }
                lines += 1;
            }
            
            out.write(close.getBytes());
            
            response = out.toByteArray();
            
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(JConv.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return response;
    }
    
}
