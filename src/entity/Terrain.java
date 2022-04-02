/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author msi
 */
public class Terrain {
    int id;

   
  
    private String type,chef,equipement;

    public Terrain() {
    }

    public Terrain(int id_terrain, String type, String chef, String equipement) {
        this.id = id_terrain;
        this.type = type;
        this.chef = chef;
        this.equipement = equipement;
    }

    public Terrain(String type, String chef, String equipement) {
        this.type = type;
        this.chef = chef;
        this.equipement = equipement;
    }

    public int getId_terrain() {
        return id;
    }

    public void setId_terrain(int id_terrain) {
        this.id = id_terrain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }


}