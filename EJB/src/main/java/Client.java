import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 13.04.2017.
 */
public class Client extends JFrame {
    private JPanel rootPanel;
    private JButton addingInformationFileButton;
    private JButton viewingDataFileButton;
    private JTextPane textFile;
    private JButton clearTextButton;

    private void createUIComponents() {

    }

    public Client(final TestClient run) {
        super("Lab2 - EJB 3");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewingDataFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textFile.setText(run.viewingDataFile());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        addingInformationFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textFile.setText(run.addingInformationFile(textFile.getText()));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        clearTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textFile.setText("");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        setVisible(true);
    }
}
