package project.rendezvous.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import other.ConsoleColors;
import project.rendezvous.registration.userDescription.UserDescription;
import project.rendezvous.registration.userDescription.UserDescriptionRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Base64;

@Service
public class FileManager {

    private UserDescriptionRepository userDescriptionRepository;

    @Autowired
    public void setUserDescriptionRepository(UserDescriptionRepository userDescriptionRepository) {
        this.userDescriptionRepository = userDescriptionRepository;
    }

    private final String basicPath = "C:\\Users\\Hawke\\Desktop\\rendezVous\\disk\\";

    // It allows creating a folder for storing pictures for the user if the folder did not exist before
    public void createFolder(String email, Picture picture){

        Path path = Paths.get(basicPath + email);

        if(!Files.exists(path)){
            try {
                System.out.println("Directory is created !");
                Files.createDirectories(path);

            } catch (IOException e){
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        } else {
            System.out.println("Folder exist !");
        }

    }

    public String serializeObjectAndSaveInDirectory(String email, Picture picture){

        UserDescription userDescription = userDescriptionRepository.findByEmail(email);
        int imgNumber =  userDescription.getNumberOfImg();

        String imgName = email + "-img-" + imgNumber;
        System.out.println("Add new img, path = " + imgName);


        try {
            String pathToImg = basicPath + email + "\\" + imgName;
            FileOutputStream fos = new FileOutputStream(pathToImg);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(picture.getBytes());
            fos.close();

            imgNumber++;
            userDescription.setNumberOfImg(imgNumber);
            userDescriptionRepository.save(userDescription);

            return pathToImg;

        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return "no path";
    }


    public Picture deserializationObjectAndGetFromDirectory(String imgPath){

        String imgInBase63 = "";

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(imgPath))){

            imgInBase63 = (String) inputStream.readObject();
//            System.out.println("Czytam: " + imgInBase63);

        }catch(Exception e){
            System.out.println(ConsoleColors.RED + e.getMessage() + ConsoleColors.RESET);
        }

        Picture picture = new Picture(imgInBase63);

        return picture;
    }

    public void base64To(Picture picture){

        BufferedImage image1 = null;

        byte[] imageBytes = Base64.getDecoder().decode(picture.getBytes());

        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        try {
            image1 = ImageIO.read(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
