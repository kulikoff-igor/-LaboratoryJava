import javax.naming.*;
import java.util.Properties;


public class TestClient {
    private SimpleBean bean;

    public TestClient() throws NamingException {
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jnp.interfaces.NamingContextFactory");
        p.setProperty(Context.PROVIDER_URL, "localhost");


        InitialContext ctx = new InitialContext();
        bean = (SimpleBean) ctx.lookup("ejb/SimpleBeanJNDI");
    }

    public String viewingDataFile() throws Exception {
        String result = bean.viewFile().toString();
        return result;
    }

    public String addingInformationFile(String text) throws Exception {

        String result = bean.addingInformation(text).toString();
        return result;
    }

    public static void main(String[] args) {

        TestClient run = null;
        try {
            run = new TestClient();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        //cli.viewingDataFile();
        Client client = new Client(run);
    }
}
