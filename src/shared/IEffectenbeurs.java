package shared;

import fontyspublisher.IRemotePropertyListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.Remote;


public interface IEffectenbeurs extends Remote {
   
    ArrayList<IFonds> getKoersen() throws RemoteException;
    public void addListener(IRemotePropertyListener listener, String property) throws RemoteException;
}