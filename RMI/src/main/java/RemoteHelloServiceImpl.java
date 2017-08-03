import java.io.*;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by admin on 10.04.2017.
 */
public class RemoteHelloServiceImpl implements RemoteHelloService {

    public static final String BINDING_NAME = "sample/HelloService";
    public File file = new File("example.txt");

    public Object viewFile() {
        String string = read();
        return string;
    }

    public Object addingInformation(String text) {
        if (!file.exists()) {
            write(text);
        } else {
            update(text);
        }
        String string = read();


        return string;
    }

    public void update(String newText) {
        StringBuilder sb = new StringBuilder();
        String oldFile = read();
        sb.append(oldFile);
        sb.append(newText);
        write(sb.toString());
    }

    public void write(String text) {

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String read() {

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(2099);
        System.out.println(" OK");

        final RemoteHelloService service = new RemoteHelloServiceImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
