package project.rendezvous.contact;

import project.rendezvous.contact.ContactForm;

import javax.mail.MessagingException;

public interface MailService {

     void sendEmail(String to, String subject, String content, boolean isHtmlContent) throws MessagingException;
     boolean sendContactForm(ContactForm contactForm, String administrationEmail) throws MessagingException;

}
