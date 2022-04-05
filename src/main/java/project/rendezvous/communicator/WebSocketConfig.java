package project.rendezvous.communicator;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//       Definiuje endpoint na które będe udeżał aby dostać sie do kolejki topic
        registry.addEndpoint("/chat");
        registry.addEndpoint("/chat").withSockJS();

        registry.addEndpoint("/recipients/update");
        registry.addEndpoint("/recipients/update").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

       // nazwa brokera
       registry.enableSimpleBroker("/queue/");

       // Dla zewnetrznego dostepu do aplikcaji
//        Najpierw odwołaj sie do app a pozniej do brokera czyli topic
       registry.setApplicationDestinationPrefixes("/app");
       registry.setUserDestinationPrefix("/users");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new UserInterceptor());
    }

}
