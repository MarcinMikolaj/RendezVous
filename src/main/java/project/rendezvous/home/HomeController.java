package project.rendezvous.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value="/")
    public String homeGET1(){
        return "home";
    }

    @GetMapping(value="/home")
    public String homeGET2(){
        return "home";
    }

}
