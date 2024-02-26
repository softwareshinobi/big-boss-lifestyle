package digital.agenteight.bigbosslifestyle.user;

import digital.agenteight.bigbosslifestyle.experience.Budget;
import digital.agenteight.bigbosslifestyle.user.MyUserRepository;
import digital.agenteight.bigbosslifestyle.user.MyUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = SQLException.class)
public class MyUserService {

    MyUserRepository userRepository;

    @Autowired
    public MyUserService(MyUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public MyUser authenticateUser(MyUser myUser)  {
        Optional<MyUser> myUserOptional = this.userRepository.findByEmailAllIgnoreCase(myUser.getEmail());
        if (myUserOptional.isPresent() && myUserOptional.get().getPassword().equals(myUser.getPassword())) {
            return myUserOptional.get();
        } else {
            return null;
        }
    }

    public MyUser saveUser(MyUser myUser) {
        return this.userRepository.save(myUser);
    }
    public List<MyUser> getAll() {
      return this.userRepository.findAll();
    } 
    public List<MyUser> gessstAll() {
      return this.userRepository.findAll();
    } 
    
    
        public void delete(Integer id) {
          this.userRepository.deleteById(id);
    }

    public Optional<MyUser> get(Integer id) {
          return this.userRepository.findById(id);
    }
}