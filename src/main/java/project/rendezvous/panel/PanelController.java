package project.rendezvous.panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.rendezvous.panel.preferences.UserPreferences;
import project.rendezvous.registration.UserRepository;

@Controller
public class PanelController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/panel")
    public String panelGET(Model model){
        model.addAttribute("userPreferences", new UserPreferences());
    return "panel";
    }


}
