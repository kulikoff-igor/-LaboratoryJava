import javax.ejb.*;
import java.io.*;

@Stateless(name = "Example", mappedName = "ejb/SimpleBeanJNDI")
public class SimpleBeanImpl implements SimpleBean {
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
}