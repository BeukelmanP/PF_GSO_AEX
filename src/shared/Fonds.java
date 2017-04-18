/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Fred
 */
public class Fonds implements IFonds, Serializable{
    //props 
    private String naam;
    private double koers;
    
    //get set
    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }
    
    //constructor
    public Fonds(String naam, double koers){
        this.naam = naam;
        this.koers = koers;
    }

    //methods
    public void setRndKoers(Random rnd){
        koers = rnd.nextDouble();
    }
    
}
