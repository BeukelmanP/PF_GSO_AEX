package server;

import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.RemotePublisher;
import shared.Fonds;
import shared.IEffectenbeurs;
import shared.IFonds;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MockEffectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {

    private ArrayList<IFonds> koersen = new ArrayList<IFonds>();
    private Timer pollingTimer;
    private RemotePublisher publisher;

    public MockEffectenbeurs() throws RemoteException {
        koersen.add(new Fonds("Google", 102.67));
        koersen.add(new Fonds("Facebook", 1.25));

        pollingTimer = new Timer();
        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                System.out.println("calc course");
                ArrayList<IFonds> oudeFondsen = koersen;
                
                
                Random rnd = new Random();
                for (IFonds fonds : koersen) {
                    fonds.setRndKoers(rnd);
                }
                
                try {
                    publisher.inform("MockEffectenbeurs", oudeFondsen, koersen);
                    System.out.println("succesvol nieuwe koersen published");
                } catch (RemoteException ex) {
                    Logger.getLogger(MockEffectenbeurs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        publisher = new RemotePublisher();
        publisher.registerProperty("MockEffectenbeurs");

        pollingTimer.schedule(TT, 0, 2000);
        
    }

    @Override
    public ArrayList<IFonds> getKoersen() throws RemoteException {
        return koersen;
    }

    @Override
    public void addListener(IRemotePropertyListener listener, String property) throws RemoteException {
        System.out.println(listener);
        System.out.println(property);
        publisher.subscribeRemoteListener(listener, property);
    }

}
