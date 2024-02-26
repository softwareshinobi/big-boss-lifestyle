package digital.agenteight.bigbosslifestyle.user;

import digital.agenteight.bigbosslifestyle.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    
    Optional<MyUser> findByEmailAllIgnoreCase (String email);

    Optional<MyUser> findByIdAllIgnoreCase (Integer id);

    Optional<MyUser> findByFirstNameAllIgnoreCase (String firstName);

    Optional<MyUser> findByLastNameAllIgnoreCase (String lastName);

}