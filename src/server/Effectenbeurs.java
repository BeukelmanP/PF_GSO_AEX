package server;

import fontyspublisher.IRemotePropertyListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import shared.IFonds;
import shared.Fonds;
import shared.IEffectenbeurs;
import java.util.ArrayList;


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

    @Override
    public void addListener(IRemotePropertyListener listener, String property) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}