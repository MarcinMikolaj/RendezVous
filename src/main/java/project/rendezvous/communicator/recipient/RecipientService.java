package project.rendezvous.communicator.recipient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.rendezvous.panel.FileManager;
import project.rendezvous.panel.Picture;
import project.rendezvous.registration.UserDescription;
import project.rendezvous.registration.UserDescriptionRepository;
import project.rendezvous.registration.UserFriends;
import project.rendezvous.registration.UserFriendsRepository;

import java.util.List;

@Service
public class RecipientService {

    private UserFriendsRepository userFriendsRepository;
    private UserDescriptionRepository userDescriptionRepository;
    private FileManager fileManager;

    @Autowired
    public void setUserFriendsRepository(UserFriendsRepository userFriendsRepository){
        this.userFriendsRepository = userFriendsRepository;
    }

    @Autowired
    public void setUserDescriptionRepository(UserDescriptionRepository userDescriptionRepository){
        this.userDescriptionRepository = userDescriptionRepository;
    }

    @Autowired
    public void setFileManager(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public RecipientList getRecipientList(String email){

        RecipientList recipientList = new RecipientList();

        UserFriends userFriends = userFriendsRepository.findByEmail(email);

        recipientList.setUserEmail(email);

        userFriends.getFormedPairsWithUsersList().stream()
                .filter(recipientEmail -> recipientEmail != null)
                .peek(recipientEmail -> recipientList.getRecipientList().add(createRecipient(recipientEmail))).close();

        return  recipientList;
    }

    private Recipient createRecipient(String recipientEmail){

        UserDescription userDescription = userDescriptionRepository.findByEmail(recipientEmail);
        String path = userDescription.getPathToImgList().get(0);
        Picture picture = fileManager.deserializationObjectAndGetFromDirectory(path);

        Recipient recipient = new Recipient();
        recipient.setEmail(userDescription.getEmail());
        recipient.setProfileImg(picture);
        recipient.setActive(false);

        return recipient;
    }

}


//        for(String recipientEmail: userFriends.getFormedPairsWithUsersList()){
//
//            UserDescription userDescription = userDescriptionRepository.findByEmail(recipientEmail);
//            String path = userDescription.getPathToImgList().get(0);
//            Picture picture = fileManager.deserializationObjectAndGetFromDirectory(path);
//
//            Recipient recipient = new Recipient();
//            recipient.setEmail(userDescription.getEmail());
//            recipient.setProfileImg(picture);
//            recipient.setActive(false);
//            recipientList.getRecipientList().add(recipient);
//        }
