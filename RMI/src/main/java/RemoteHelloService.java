import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by admin on 10.04.2017.
 */
public interface RemoteHelloService extends Remote {

    Object viewFile() throws RemoteException;

    Object addingInformation(String text) throws RemoteException;
}