package gso_aex;

import java.util.List;
import java.util.Random;


public class MockEffectenbeurs implements IEffectenbeurs {
    
    
    private List<IFonds> Koersen;
    
    @Override
    public List<IFonds> getKoersen() {
        Random rnd = new Random();
        for(IFonds fonds : Koersen){
           fonds.setRndKoers(rnd);
        }
        return Koersen;
    }

}