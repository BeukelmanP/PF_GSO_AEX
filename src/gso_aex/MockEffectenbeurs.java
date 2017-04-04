package gso_aex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockEffectenbeurs implements IEffectenbeurs {

    private ArrayList<IFonds> Koersen = new ArrayList<IFonds>();

    public MockEffectenbeurs() {
        Koersen.add(new Fonds("Google", 102.67));
        Koersen.add(new Fonds("Facebook", 1.25));
    }

    @Override
    public ArrayList<IFonds> getKoersen() {
        Random rnd = new Random();
        for (IFonds fonds : Koersen) {
            fonds.setRndKoers(rnd);
        }
        return Koersen;
    }

}
