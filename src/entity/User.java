/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class User {
    private int id,cin;
    private String username, prenom, genre,email,password,adresse, date_naissance;
   

    public User() {
    }

    
    public User(int id, int cin, String username, String prenom, String genre, String email, String password, String adresse, String date_naissance) {
        this.id = id;
        this.cin = cin;
        this.username = username;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
    }

    public User(int cin, String username, String prenom, String genre, String email, String password, String adresse, String date_naissance) {
        this.cin = cin;
        this.username = username;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.password = password;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
    }

    public int getId() {
        return id;
    }

    public int getCin() {
        return cin;
    }

    public String getUsername() {
        return username;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGenre() {
        return genre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDate_naissance() {
        return date_naissance;
    }
    
    
    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cin=" + cin + ", username=" + username + ", prenom=" + prenom + ", genre=" + genre + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", date_naissance=" + date_naissance + '}';
    }

   
    
    
    
}
