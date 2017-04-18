package server;

import shared.Fonds;
import shared.IEffectenbeurs;
import shared.IFonds;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    private ArrayList<IFonds> Koersen = new ArrayList<IFonds>();

    public MockEffectenbeurs() throws RemoteException {
        Koersen.add(new Fonds("Google", 102.67));
        Koersen.add(new Fonds("Facebook", 1.25));
    }

    @Override
    public ArrayList<IFonds> getKoersen() throws RemoteException {
        Random rnd = new Random();
        for (IFonds fonds : Koersen) {
            fonds.setRndKoers(rnd);
        }
        return Koersen;
    }

}
