/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entity.User;
import java.util.Date;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
               
        TextField cin = new TextField("", "Cin", 20, TextField.ANY);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
        TextField genre = new TextField("", "genre", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        Picker date_naissance = new Picker();
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        date_naissance.setDate(new Date());
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(cin),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(genre),
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator(),
                date_naissance,
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        
        User user = new User();
        
       
        next.requestFocus();
        next.addActionListener(e -> {
            
             user.setCin(Integer.parseInt(cin.getText()) );
        user.setAdresse(adresse.getText());
        user.setDate_naissance(date_naissance.getDate());
        user.setEmail(email.getText());
        user.setGenre(genre.getText());
        user.setPassword(password.getText());
        user.setUsername(username.getText());
        user.setPrenom(prenom.getText());
            
            if(service.ServiceUser.getInstance().inscrit(user)){
            
                Dialog.show("Inscription", "addes","OK",null);
            }
            
        });
    }
    
}
