/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import entity.User;

import java.util.ArrayList;
import service.ServiceUser;

/**
 *
 * @author Ahmed
 */
public class ListeUserr extends BaseForm {

   
        
    public ListeUserr(Resources res) {
        setTitle("List avis");
        Form hi2 = new Form ("Mark",new BoxLayout(BoxLayout.Y_AXIS));
        hi2.getToolbar().addCommandToLeftBar("back",null,(evt2)->{
             previous.showBack();
                    });
               
           ArrayList<User>list = ServiceUser.getInstance().ShowAllExercices();
           for(User u:list){
               TableModel model=new DefaultTableModel(new String[] {"cin", "Nom", "prenom", "genre","adresse","email"},new Object[][]{
                   {Integer.toString(u.getCin()),u.getUsername(),u.getPrenom(),u.getGenre(),u.getAdresse(),u.getEmail()}
                       
               }){
                   public boolean isCellEditable(int row,int col){
                    return col!=0;   
               }
                       };
               Table tab=new Table (model);
               add(tab);
          
           
           //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette User ?","Oui","Annuler")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service User
                if(ServiceUser.getInstance().deleteUser(u.getId())) {
                    new ListeUserr(res).show();
                }
           
        });
        add(lSupprimer);

    
    
    //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        
       
        
        add(lModifier);
    }
    }

    private static class previous {

        private static Command showBack() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public previous() {
        }
    }

    
   
}
