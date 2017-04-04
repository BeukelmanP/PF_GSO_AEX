package gso_aex;

import java.util.ArrayList;
import java.util.List;


public class Effectenbeurs implements IEffectenbeurs {

    private ArrayList<IFonds> Koersen;
    
    
    public Effectenbeurs()
    {
    Koersen.add(new Fonds("Google", 102.67));
    Koersen.add(new Fonds("Facebook", 1.25));
    }
    @Override
    public ArrayList<IFonds> getKoersen() {
        return Koersen;
    }
}