import javax.ejb.*;


@Remote
public interface SimpleBean {
    Object viewFile();

    Object addingInformation(String text);

}
