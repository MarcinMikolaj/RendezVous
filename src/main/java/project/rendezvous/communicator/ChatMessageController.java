package project.rendezvous.communicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import project.rendezvous.communicator.messages.Conversation;
import project.rendezvous.communicator.messages.ConversationService;
import project.rendezvous.communicator.messages.UpdateConversationRequestPojo;
import project.rendezvous.communicator.recipient.RecipientList;
import project.rendezvous.communicator.recipient.RecipientService;

import java.security.Principal;

@Controller
public class ChatMessageController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private RecipientService recipientService;
    private ConversationService conversationService;

    @Autowired
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Autowired
    public void setRecipientService(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @Autowired
    public void setConversationService(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @MessageMapping("/chat")
    public void messageGET(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor sha, Principal principal, @Header("simpSessionId") String sessionId) throws Exception{
        conversationService.saveConversation(principal.getName(), chatMessage.getName(), chatMessage);
        conversationService.saveConversation(chatMessage.getName(), principal.getName(), chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getName(),"/queue/messages", chatMessage);
    }


    @MessageMapping("/recipients")
    public void recipientListGET(SimpMessageHeaderAccessor sha, Principal principal, @Header("simpSessionId") String sessionId) throws Exception{
          RecipientList recipientList = recipientService.getRecipientList(principal.getName());
          simpMessagingTemplate.convertAndSendToUser(principal.getName(),"/queue/recipients", recipientList);
    }

    @MessageMapping("/conversation")
    public void getMessageConversation(@Payload UpdateConversationRequestPojo request, Principal principal) throws Exception{
        Conversation conversation = conversationService.getConversation(principal.getName(), request.getRecipientEmail());
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/conversation", conversation);
    }

    @MessageMapping("/videochat")
    public void videochat(@Payload WebrtcConnectionNegotiateMessage webrtcConnectionNegotiateMessage, Principal principal) throws Exception {
        System.out.println("I get message: " + webrtcConnectionNegotiateMessage.toString());
        simpMessagingTemplate.convertAndSendToUser(webrtcConnectionNegotiateMessage.getTo(),"/queue/videochat",webrtcConnectionNegotiateMessage);
    }

}
