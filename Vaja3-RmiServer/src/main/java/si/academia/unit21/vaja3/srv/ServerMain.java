package si.academia.unit21.vaja3.srv;

import si.academia.unit21.vaja3.itf.IAuthorService;
import si.academia.unit21.vaja3.svc.AuthorService;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {

    public static void main(String[] args) {
        AuthorService svc = new AuthorService();
        try {
            IAuthorService stub = (IAuthorService) UnicastRemoteObject.exportObject(svc, 0);
            Registry rmiReg = LocateRegistry.getRegistry();
            rmiReg.bind("AuthorService", stub);
            System.out.println("Server ready");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        System.out.println("Shutting down server");
                        rmiReg.unbind("AuthorService");
                    } catch (RemoteException e){
                        e.printStackTrace();
                    } catch (NotBoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
