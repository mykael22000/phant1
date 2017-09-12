package au.com.mik.phant1;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Indexer {

    private String cluster_name;
    private String index_name;
    private String type_name;
    
    public Indexer(String cluster, String index, String type) {
        cluster_name = cluster;
        index_name = index;
        type_name = type;
    };
    
    public void indexAlert(byte[] jstring) {    

        try {
            // Set up for connectivity to elastic
        
            InetSocketTransportAddress cluster = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);
            System.out.println("Address is :" + cluster);
            
            Settings my_settings = Settings.builder()
                    .put("cluster.name", cluster_name)
                    .build();

            System.out.println("Cluster name is:" + cluster_name);
            System.out.println("Cluster name is:" + my_settings.get("cluster.name", "blank"));
            
            TransportClient client = new PreBuiltTransportClient(my_settings);            
            System.out.println("Client is :" + client);
            
            // Tell the client where the cluster is:
            
            client.addTransportAddress(cluster);
            
            // Send to Elastic
        
            IndexResponse response = client.prepareIndex(index_name,type_name)
                .setSource(jstring)
                .get();
            
            System.out.println(response);
            
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Phant1.class.getName()).log(Level.SEVERE, null, e);
        }    

        // All done
        
        return;
    }
}
