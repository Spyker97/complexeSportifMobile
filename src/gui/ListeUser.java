/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
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
public class ListeUser extends BaseForm {

   
        
    public ListeUser(Resources res) {
        ArrayList<User> lu = ServiceUser.getInstance().ShowAllExercices();
        String [][] t = new String [lu.size()][6];
        int i =0;
        for (User u : lu){
          t[i][0]=Integer.toString(u.getCin()) ;
          t[i][1]=u.getUsername();
          t[i][2]=u.getPrenom();
          t[i][3]=u.getGenre();
          t[i][4]=u.getAdresse();
          t[i][5]="dsds";
                 
         i++;       
        }
           
       TableModel model = new DefaultTableModel(new String[] {"cin", "Nom", "prenom", "genre","adresse","email"},t) {
        public boolean isCellEditable(int row, int col) {
            return col != 0;
        }
    };
            
            
            Table table = new Table(model) {
    @Override
    protected Component createCell(Object value, int row, int column, boolean editable) { // (1)
        Component cell;
        if(row == 1 && column == 1) { // (2)
            Picker p = new Picker();
            p.setType(Display.PICKER_TYPE_STRINGS);
            p.setStrings("Row B can now stretch", "This is a good value", "So Is This", "Better than text field");
            p.setSelectedString((String)value); // (3)
            p.setUIID("TableCell");
            p.addActionListener((e) -> getModel().setValueAt(row, column, p.getSelectedString())); // (4)
            cell = p;
        } else {
            cell = super.createCell(value, row, column, editable);
        }
        if(row > -1 && row % 2 == 0) { // (5)
            // pinstripe effect 
            cell.getAllStyles().setBgColor(0xeeeeee);
            cell.getAllStyles().setBgTransparency(255);
        }
        return cell;
    }
 
    @Override
    protected TableLayout.Constraint createCellConstraint(Object value, int row, int column) {
        TableLayout.Constraint con =  super.createCellConstraint(value, row, column);
        if(row == 1 && column == 1) {
            con.setHorizontalSpan(2);
        }
        con.setWidthPercentage(15);
        return con;
    }
};
            add(table);
            
    }

    private static class suprrimerImage {

        public suprrimerImage() {
        }
    }

    private static class RIGHT {

        public RIGHT() {
        }
    }
    
    
    

}
