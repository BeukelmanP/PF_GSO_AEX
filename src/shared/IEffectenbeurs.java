package shared;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.Remote;


public interface IEffectenbeurs extends Remote {
   
    ArrayList<IFonds> getKoersen() throws RemoteException;
    

}