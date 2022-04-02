/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entity.Terrain;
import service.ServiceTerrain;

/**
 *
 * @author Lenovo
 */
public class ModifierTerrainForm extends Form {
    
    Form current;
    public ModifierTerrainForm(Terrain r,Form previous) {
        setTitle("Modifier Terrain");
        
        TextField type = new TextField(r.getType(), "type" , 20 , TextField.ANY);
        TextField chef = new TextField(r.getChef(), "chef" , 20 , TextField.ANY);
        TextField equipement = new TextField(r.getEquipement(), "equipement" , 20 , TextField.ANY);
        
        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setType(type.getText());
           r.setChef(chef.getText());
           r.setEquipement(equipement.getText());
       
       //appel fonction modfier Terrain men service
       
       if(ServiceTerrain.getInstance().modifierTerrain(r)) { // if true
           new ListTerrainForm(previous).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListTerrainForm(previous).show();
       });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(type),
                new FloatingHint(chef),
                new FloatingHint(equipement),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        
        
    }
}

