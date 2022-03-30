/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entity.User;
import java.util.Collection;

/**
 *
 * @author Ahmed
 */
public class ServiceUser {
    
    public Collection <User> users;
    
    
    //singleton 
    public static ServiceUser instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private final ConnectionRequest req;
    
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }
    
    public boolean inscrit(User u){
        
            req.setUrl("http://127.0.0.1:8000/inscritjson?email=ossama@gmail.com&username="+u.getUsername()+"&prenom="+u.getPrenom()+"&password="+u.getPassword()+"&genre="+u.getGenre()+"&cin="+Integer.toString(u.getCin())+"&date_naissane="+u.getDate_naissance().toString() );
        System.out.println(u.getDate_naissance().toString());
            req.setPost(false);
        /*req.addArgument("username", "prenom");
        req.addArgument("prenom", "jun");
        //req.addArgument("date_naissane", "prenom");
        req.addArgument("cin", "12345678");
        req.addArgument("genre", "prenom");
        req.addArgument("password", "prenom");
        req.addArgument("email", "prenom@gmail.com");*/
        
        
             req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    
                    resultOk=req.getResponseCode()==200;
                    req.removeResponseListener(this);
                                System.out.println("hhhhhhhhhhhhhhh");

                }
                
            }
        );
      
      
      //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
     
        
    
        
        
    }
}
