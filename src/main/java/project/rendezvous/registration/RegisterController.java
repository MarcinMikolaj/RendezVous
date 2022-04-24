package project.rendezvous.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/register")
    public String registerGet(Model model){

        model.addAttribute("user", new User());

        return "register";
    }

    @RequestMapping(path = "/register/user/create")
    public String getAccount(@Valid @ModelAttribute User user, BindingResult result){

        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(error -> System.out.println(error.getDefaultMessage()));
            return "register";

        }else{
//            System.out.println(user.toString());
            userService.createAccount(user);
            return "redirect:/panel";

        }

    }

}
