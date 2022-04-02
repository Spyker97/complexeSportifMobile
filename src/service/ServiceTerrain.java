/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entity.Terrain;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceTerrain{
    
    //singleton 
    public static ServiceTerrain instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceTerrain getInstance() {
        if(instance == null )
            instance = new ServiceTerrain();
        return instance ;
    }
    
    
    
    public ServiceTerrain() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutTerrain(Terrain terrain) {
        
        req.setUrl("http://127.0.0.1:8000/addterrainJSON?type="+terrain.getType()+"&chef="+terrain.getChef()+"&equipement="+terrain.getEquipement());
        
        req.setPost(false);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Terrain>affichageTerrain() {
        ArrayList<Terrain> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"allTerrains";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapTerrain = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapTerrain.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Terrain re = new Terrain();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String type = obj.get("type").toString();
                        String chef = obj.get("chef").toString();
                        String equipement = obj.get("equipement").toString();
                        re.setId_terrain((int)id);
                        re.setType(type);
                        re.setChef(chef);
                        re.setEquipement(equipement);
                        
                        /*Date 
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        re.setDate(dateString);
                        */
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        System.out.println(result);
        return result;
        
        
    }
    
    
    
    //Detail Terrain bensba l detail n5alihoa lel5r ba3d delete+update
    
    public Terrain DetailRecalamation( int id , Terrain terrain) {
        
        String url = Statics.BASE_URL+"terrainid/"+id;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                terrain.setType(obj.get("type").toString());
                terrain.setChef(obj.get("chef").toString());
                terrain.setEquipement(obj.get("equipement").toString());
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return terrain;
        
        
    }
    
    
    //Delete 
    public boolean deleteTerrain(int id ) {
        String url = Statics.BASE_URL +"deleteterrrainJSON/"+id;
        
        req.setUrl(url);
        
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
    public boolean modifierTerrain(Terrain terrain) {
        req.setUrl("http://127.0.0.1:8000/updateterrainJSON?id="+terrain.getId_terrain()+"&type="+terrain.getType()+"&chef="+terrain.getChef()+"&equipement="+terrain.getEquipement());
        
        req.setPost(false);
        
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
    

    
}