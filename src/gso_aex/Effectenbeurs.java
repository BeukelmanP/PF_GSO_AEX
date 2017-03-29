package gso_aex;

import java.util.List;


public class Effectenbeurs implements IEffectenbeurs {

    private List<IFonds> Koersen;
    
    @Override
    public List<IFonds> getKoersen() {
        return Koersen;
    }
}