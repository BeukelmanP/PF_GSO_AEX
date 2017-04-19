package gso_aex;

import fontyspublisher.IRemotePropertyListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import shared.IFonds;
import shared.IEffectenbeurs;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private ArrayList<IFonds> koersen = new ArrayList<>();
    private static final String bindingName = "MockEffectenbeurs";

    public List<IFonds> getKoersen() {
        return koersen;
    }

    public BannerController(AEXBanner banner, String ipAddress, int portNumber) throws RemoteException {

        this.banner = banner;

        effectenbeurs = getRemoteMock(ipAddress, portNumber);

        //subscirbe this as listener to MockEffectenbeurs in registery
        System.out.println("pre listner");
        effectenbeurs.addListener(this, "MockEffectenbeurs");
        System.out.println("after listner");
    }

    public void stop() {

    }

    private IEffectenbeurs getRemoteMock(String ipAddress, int portNumber) {
        Registry registry;
        IEffectenbeurs beurs = null;

        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
            beurs = (IEffectenbeurs) registry.lookup(bindingName);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (beurs == null) {
            System.out.println("its null");
        } else{
            System.out.println("its not null");
        }

        return beurs;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        banner.setKoersen(evt.getNewValue().toString());
        System.out.println("updated banner with remote push");
        System.out.println(evt.getNewValue().toString());
    }
}
