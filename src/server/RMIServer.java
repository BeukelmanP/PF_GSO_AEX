/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fred
 */
public class RMIServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for student administration
    private static final String bindingName = "MockEffectenbeurs";

    // References to registry and student administration
    private Registry registry = null;
    private MockEffectenbeurs mockEffectenBeurs = null;

    // Constructor
    public RMIServer() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        try {
            // Create  MockEffectenbeurs
            mockEffectenBeurs = new MockEffectenbeurs();
        } catch (RemoteException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }


        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind student administration using registry
        try {
            registry.rebind(bindingName, mockEffectenBeurs);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        InetAddress localhost;
        try {
            localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        // Print IP addresses and network interfaces
        printIPAddresses();

        // Create server
        RMIServer server = new RMIServer();
    }
}
