package project.rendezvous.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import project.rendezvous.contact.ContactForm;
import project.rendezvous.contact.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private Validator validator;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setValidator(Validator validator){
        this.validator = validator;
    }

    @Override
    public void sendEmail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, isHtmlContent);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean sendContactForm(ContactForm contactForm, String administrationEmail) throws MessagingException {

        Set<ConstraintViolation<ContactForm>> errors = validator.validate(contactForm);

        if(!errors.isEmpty()) {
            return false;
        }else {

            File convFile = null;
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            try{
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
                mimeMessageHelper.setTo(administrationEmail);
                mimeMessageHelper.setSubject(contactForm.getTitle());
                mimeMessageHelper.setText(contactForm.getFormContent(), false);



                if(contactForm.getMultipartFile().isEmpty() == false) {
                    convFile = new File(System.getProperty("java.io.tmpdir")+ contactForm.getMultipartFile().getOriginalFilename());
                    contactForm.getMultipartFile().transferTo(convFile);
                    mimeMessageHelper.addAttachment(convFile.getName(),convFile);
                }

                javaMailSender.send(mimeMessage);
                convFile.delete(); // Remove filer after use

            } catch (MessagingException | IOException e){
                e.printStackTrace();
            }

            return true;
        }

    }
}
