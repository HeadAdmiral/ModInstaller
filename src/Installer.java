import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by masti on 5/29/2016.
 */
public class Installer extends JFrame{
    private JButton installModsButton;
    private JPanel rootPanel;
    private JEditorPane editorPane1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public Installer(){
        super("Installer");
        setDefaultLookAndFeelDecorated(true);
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 150);
        setResizable(false);
        editorPane1.setText("Once you click install, the program may seem unresponsive, but it's just downloading stuff.");


        installModsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Install install = new Install();
                if (install.mac == true){
                    editorPane1.setText("Non-Windows OS detected. This program is designed to run only on Windows.");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
                try {
                    setTitle("Installing...");
                    install.downloadFile();
                    setTitle("Files Successfully Installed!");
                    if (install.exists == true){
                        setSize(1000, 500);
                        editorPane1.setText("All files have been downloaded. "+ "\n" + "\n" + "There was an existing .minecraft folder found at %AppData%/Roaming. " + "\n" +
                                "If you wish to keep your files (saves, screenshots, etc.) then do the following: " + "\n" + "Open the start menu and type %AppData%. " + "\n" +
                                "Open on the Roaming folder. " + "\n" + "Your old .minecraft folder was renamed to .minecraft(old). " + "\n" + "Collect your files from there and do what you want with them.");
                    }
                    else{
                        editorPane1.setText("All files have been downloaded.");
                    }
                } catch (Exception e1) {
                    System.exit(0);
                }
                finally{
                    File file = new File("C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/.minecraft.zip");
                    file.deleteOnExit();
                }
            }
        });
        setVisible(true);
    }
}
