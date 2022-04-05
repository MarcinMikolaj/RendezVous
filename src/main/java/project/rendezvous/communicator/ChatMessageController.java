package project.rendezvous.communicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import project.rendezvous.communicator.recipient.RecipientList;
import project.rendezvous.communicator.recipient.RecipientService;

import java.security.Principal;

@Controller
public class ChatMessageController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private RecipientService recipientService;

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Autowired
    public void setRecipientService(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    // Nasz js pcha wiadomość do tej metody
    @MessageMapping("/chat")
    public void messageGET(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor sha, Principal principal, @Header("simpSessionId") String sessionId) throws Exception{
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getName(),"/queue/messages", chatMessage);
    }


    @MessageMapping("/recipients/update")
    public void recipientListGET(SimpMessageHeaderAccessor sha, Principal principal, @Header("simpSessionId") String sessionId) throws Exception{
          RecipientList recipientList = recipientService.getRecipientList(principal.getName());
          simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue/recipients", recipientList);
    }

}
