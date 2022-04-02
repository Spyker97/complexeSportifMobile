/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import entity.Terrain;
import entity.Terrain;
import service.ServiceTerrain;
import java.util.ArrayList;

/**z
 *
 * @author bhk
 */
public class ListTerrainForm extends BaseForm {

    public ListTerrainForm(Form previous) {
        setTitle("List Terrain");
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
           ArrayList<Terrain>list = ServiceTerrain.getInstance().affichageTerrain();
           for(Terrain a:list){
               TableModel model=new DefaultTableModel(new String[] {"type","chef","equipement"},new Object[][]{
                   {a.getType(),a.getChef(),a.getEquipement()}
                       
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
            
            if(dig.show("Suppression","Vous voulez supprimer cette Terrain ?","Oui","Annuler")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceTerrain.getInstance().deleteTerrain(a.getId_terrain())) {
                    new ListTerrainForm(previous).show();
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
        
        
        lModifier.addPointerPressedListener(l -> {
            //System.out.println("hello update");
            new ModifierTerrainForm(a,previous).show();
        });
        Form hi2 = new Form ("Mark",new BoxLayout(BoxLayout.Y_AXIS));
        hi2.getToolbar().addCommandToLeftBar("back",null,(evt2)->{
             previous.showBack();
                    });
       
        
        add(lModifier);
    }
    }

    ListTerrainForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

