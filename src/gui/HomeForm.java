/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import entity.Terrain;

/**
 *
 * @author bhk
 */
public class HomeForm extends BaseForm {

    Form current;

    public HomeForm() {
        current = this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.yCenter());

        Container ctn = new Container(new FlowLayout(CENTER, CENTER));

        Label lb = new Label("Bienvenue dans notre Application ");
        ctn.add(lb);
        current.add(ctn);
        getToolbar().addCommandToSideMenu("home", null, ((evt) -> {
        }));
        getToolbar().addCommandToSideMenu("Gestion des Terrain ", null, (e) -> {
            new AddterrainForm(current).show();

        });


    }

    HomeForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
