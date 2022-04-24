package project.rendezvous.registration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import project.rendezvous.registration.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

//    email;
//    password;
//    nick;
//    gender;
//    age;
//    termsOfUse;

    User findByEmail(String email);
    List<User> findAllByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByNick(String nick);

    List<User> findByAge(int age);
    List<User> findByAgeBetween(int ageGT, int ageLT);
    List<User> findByAgeBetweenOrderByAgeAsc(int ageGT, int ageLT);

    @Query("{ 'nick' : ?0 }")
    List<User> findByNick(String nick);

}
