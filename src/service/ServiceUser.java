/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.JSONParser;
import java.io.IOException;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entity.User;
import gui.NewsfeedForm;
import gui.SessionManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Ahmed
 */
public class ServiceUser {
    
    public ArrayList <User> users;
    
    
    //singleton 
    public static ServiceUser instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }
    
    public boolean inscrit(User u){
        
            req.setUrl("http://127.0.0.1:8000/inscritjson?email="+u.getEmail()+"&username="+u.getUsername()+"&prenom="+u.getPrenom()+"&password="+u.getPassword()+"&genre="+u.getGenre()+"&cin="+Integer.toString(u.getCin())+"&date_naissance="+u.getDate_naissance()+"&adresse="+u.getAdresse() );
        
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
    
    public ArrayList<User> parseExercice(String jsonText){
        try {
            System.out.println("yimchiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> exercicesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)exercicesListJson.get("root");
            for(Map<String,Object> obj : list){
                User u = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float cin = Float.parseFloat(obj.get("cin").toString());
                        String username = obj.get("username").toString();
                        String date_naissance = obj.get("date_naissance").toString();
                        String prenom = obj.get("prenom").toString();
                        String adresse = obj.get("adresse").toString();
                        String email = obj.get("email").toString();
                        String genre = obj.get("genre").toString();
                        
                        u.setId((int)id);
                        u.setCin((int)cin);
                        u.setUsername(username);
                        u.setPrenom(prenom);
                        u.setAdresse(adresse);
                        u.setEmail(email);
                        u.setGenre(genre);
                users.add(u);
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("error in parseExercice");
        }
        return users;
    }
    
    public ArrayList<User> ShowAllExercices(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"exercice";
        System.out.println("===>"+url);
        req.setUrl("http://127.0.0.1:8000/alluser");
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseExercice(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           System.out.println("hahah");
        return users;
    }
    
    
    
    //Delete 
    public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"deleteuserjson/"+id;
        
        req.setUrl("http://127.0.0.1:8000/deleteuserjson/"+id);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    //Update 
    public boolean modifierUser(User u) {
        String url = Statics.BASE_URL +"updateavisjson?id="+u.getId()+"&username="+u.getUsername()+"&prenom="+u.getPrenom()+"&password="+u.getPassword()+"&genre="+u.getGenre()+"&cin="+Integer.toString(u.getCin())+"&date_naissane="+u.getDate_naissance().toString()+"&adresse="+u.getAdresse();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    /*User u = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float cin = Float.parseFloat(obj.get("cin").toString());
                        String username = obj.get("username").toString();
                        String prenom = obj.get("prenom").toString();
                        String adresse = obj.get("adresse").toString();
                        String email = obj.get("email").toString();
                        String genre = obj.get("genre").toString();
                        
                        u.setId((int)id);
                        u.setCin((int)cin);
                        u.setUsername(username);
                        u.setPrenom(prenom);
                        u.setAdresse(adresse);
                        u.setEmail(email);
                        u.setGenre(genre);*/
    
 
    
    //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
      
        //String url = Statics.BASE_URL+"/signinjson?email="+email.getText().toString()+"&password="+password.getText().toString();
        req.setUrl("http://127.0.0.1:8000/signinjson?email="+email.getText()+"&password="+password.getText());
        //req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setPost(false);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                
                    new NewsfeedForm(rs).show();
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setPost(false);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
    
    
    
    
    
    
    
    /*public void signin(TextField email,TextField password ) {
        User u = new User();
        String url = Statics.BASE_URL+"signinjson?email="+email.getText().toString()+"&password="+password.getText().toString();
        req.setUrl("http://127.0.0.1:8000/signinjson?email="+u.getEmail()+"&password="+u.getPassword());
        //req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            try {
            if(email.getText().equals("")||email.getText().equals("")) {
                Dialog.show("Echec d'authentification","Veuillez remplir les champs","OK",null);
            }else if((json.equals("password not found"))||(json.equals("user not found"))) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
               
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
               
         
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }*/
    
    
    
    
    
}
