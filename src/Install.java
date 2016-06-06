import org.apache.commons.io.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class Install {
    public boolean exists = true;
    public boolean mac = System.getProperty("os.name") == "Windows";
    public void downloadFile() throws IOException{
        try {
            String finalDirectory = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/.minecraft";
            File dir = new File(finalDirectory);
            String roamingDirectory = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming";
            String zipDirectory = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/.minecraft.zip";

            if (dir.isDirectory()){
                String newDirName = dir.toString() + "(old)";
                exists = true;
            }
            //Create unzipper
            UnzipUtility unzipUtility = new UnzipUtility();

            //Create destination file
            String destination = ".minecraft.zip";
            File dest = new File(destination);

            //Create desired URL
            URL url = new URL("http://download850.mediafire.com/ub0cywat2vgg/z2u724mhek5uxmd/.minecraft.zip");
            //Download the file at URL to destination folder
            download(destination, url);

            //Move file from src directory to desired location
            FileUtils.moveFileToDirectory(dest, new File(roamingDirectory), false);
            System.out.println("File moved, now unzipping");

            //Unzip file
            unzipUtility.unzip(zipDirectory, finalDirectory);

        }
        catch(FileExistsException e){
            System.out.println(".minecraft.zip already exists!");
        }


    }
    public void download(String filename, URL url){
        try {
            String fileName = filename; //The file that will be saved on your computer
            URL link = url; //The file that you want to download
            //Code to download
            InputStream in = new BufferedInputStream(link.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(response);
            fos.close();
            //End download code

            System.out.println("Finished");
        }
        catch(Exception e){
            System.out.println("Something happened");
        }
    }
    public static void main(String[] args) throws IOException {
        Installer installer = new Installer();

    }
}
