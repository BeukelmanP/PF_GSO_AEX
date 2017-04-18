package gso_aex;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import shared.IFonds;
import shared.IEffectenbeurs;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.MockEffectenbeurs;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BannerController {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private ArrayList<IFonds> koersen = new ArrayList<>();
    private Registry registry = null;
    private static final String bindingName = "MockEffectenbeurs";

    public List<IFonds> getKoersen() {
        return koersen;
    }

    public BannerController(AEXBanner banner, String ipAddress, int portNumber) {

        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        this.banner = banner;

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Print contents of registry
        if (registry != null) {
            printContentsRegistry();
        }

        try {
            effectenbeurs = (IEffectenbeurs) registry.lookup(bindingName);
        } catch (RemoteException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Print result binding student administration
        if (effectenbeurs != null) {
            System.out.println("Client: Student administration bound");
        } else {
            System.out.println("Client: Student administration is null pointer");
        }

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (effectenbeurs != null) {
                        koersen = effectenbeurs.getKoersen();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        pollingTimer.schedule(TT, 0, 1000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }
}
