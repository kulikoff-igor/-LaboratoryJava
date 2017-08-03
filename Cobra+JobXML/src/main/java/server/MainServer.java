package server;


import org.omg.CORBA.Object;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.lang.*;

/**
 * Created by admin on 19.04.2017.
 */
public class MainServer {
    public static void main(String args[])
    {
        try
        {
            ORB orb = ORB.init(args, null);

            ServerServant testRef = new ServerServant();
            orb.connect( testRef);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");
            NamingContext ncRef =
                    NamingContextHelper.narrow(objRef);

            NameComponent nc = new NameComponent("ServerCobra", "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, testRef);

            java.lang.Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }

        }
        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
}
