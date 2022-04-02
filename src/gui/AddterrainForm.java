/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entity.Terrain;
import service.ServiceTerrain;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class AddterrainForm extends Form{

    public AddterrainForm(Form previous) {
        setTitle("Add a new Terrain");
        setLayout(BoxLayout.y());
        
        TextField type = new TextField("","type");
        TextField chef= new TextField("", "chef");
        TextField equipement= new TextField("", "equipement");
        Button btnValider = new Button("Add terin");
        Button btnAfficher = new Button("Afficher terin");
        btnAfficher.addActionListener((evt) -> {
            new ListTerrainForm(previous).show();
        });
       btnValider.addActionListener((e) -> {
            
            
            try {
                
                if(type.getText().equals("") || chef.getText().equals("")|| equipement.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    
                    //njibo iduser men session (current user)
                    Terrain a = new Terrain(String.valueOf(type.getText()
                                  ),
                                  String.valueOf(chef.getText()),
                                  String.valueOf(equipement.getText()));
                    
                    System.out.println("data  Terrain == "+a);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceTerrain.getInstance().ajoutTerrain(a);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListTerrainForm
                    //new ListReclamationForm(res).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        addAll(type,chef,equipement,btnValider,btnAfficher);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
