package project.rendezvous.registration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.rendezvous.constraints.Email;
import project.rendezvous.constraints.EmailExist;
import project.rendezvous.constraints.NickExist;
import project.rendezvous.constraints.Password;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;

@Document
public class User implements UserDetails {

    @Id
    private String id;

    @Email
    @EmailExist
    private String email;

    @Password
    private String password;

    @NotBlank
    @NickExist
    private String nick;

    @NotBlank
    private String gender;

    @Min(value = 18)
    private int age;

    @AssertTrue
    private boolean termsOfUse;

    private String role;

    public User() {
    }

    public User(String email, String password, String nick, String gender, int age, boolean termsOfUse, String role) {
        this.email = email;
        this.password = password;
        this.nick = nick;
        this.gender = gender;
        this.age = age;
        this.termsOfUse = termsOfUse;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(boolean termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", termsOfUse=" + termsOfUse +
                ", role='" + role + '\'' +
                '}';
    }
}
