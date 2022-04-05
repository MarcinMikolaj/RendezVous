package project.rendezvous.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

// The controller supports the application's contact form
@Controller
public class ContactController {

    private MailService mailService;

    @Autowired
    public ContactController(MailService mailService){
        this.mailService = mailService;
    }

    @GetMapping(value = "/contact")
    public String getContact(Model model){
        model.addAttribute("contactForm" , new ContactForm());
        return "contact";
    }

    @RequestMapping(path = "/contact/form/send", method = RequestMethod.POST)
    public String getContactForm(@Valid @ModelAttribute ContactForm contactForm, BindingResult result){

        if(result.hasErrors()){
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "error";
        }else{
            try {
                mailService.sendContactForm(contactForm, "rendez.vous.poland@gmail.com");
                System.out.println(contactForm.toString());
                return "redirect:/contact";
            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("Nie udało się wysłać");
                return "error";
            }
        }


    }

}
