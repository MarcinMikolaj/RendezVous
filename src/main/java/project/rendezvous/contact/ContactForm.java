package project.rendezvous.contact;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.AssertTrue;

import org.springframework.web.multipart.MultipartFile;
import project.rendezvous.constraints.Email;

public class ContactForm {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String title;
    @NotBlank
    private String formContent;
    private String question;
    private MultipartFile multipartFile;
    @AssertTrue
    private boolean agreement;


    public ContactForm(){}

    public ContactForm(String name, String email, String title, String formContent, String question, boolean agreement) {
        this.name = name;
        this.email = email;
        this.title = title;
        this.formContent = formContent;
        this.question = question;
        this.agreement = agreement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormContent() {
        return formContent;
    }

    public void setFormContent(String formContent) {
        this.formContent = formContent;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", formContent='" + formContent + '\'' +
                ", question='" + question + '\'' +
                ", agreement=" + agreement +
                '}';
    }
}
