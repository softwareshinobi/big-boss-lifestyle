package digital.agenteight.bigbosslifestyle.account;

import digital.agenteight.bigbosslifestyle.experience.Budget;
import digital.agenteight.bigbosslifestyle.account.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import digital.agenteight.bigbosslifestyle.account.AccountRepository;

@Service
@Transactional(rollbackOn = SQLException.class)
public class AccountService {

    AccountRepository userRepository;

    @Autowired
    public AccountService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Account authenticateUser(Account myUser)  {
        Optional<Account> myUserOptional = this.userRepository.findByEmailAllIgnoreCase(myUser.getEmail());
        if (myUserOptional.isPresent() && myUserOptional.get().getPassword().equals(myUser.getPassword())) {
            return myUserOptional.get();
        } else {
            return null;
        }
    }

    public Account saveUser(Account myUser) {
        return this.userRepository.save(myUser);
    }
    public List<Account> getAll() {
      return this.userRepository.findAll();
    } 
    public List<Account> gessstAll() {
      return this.userRepository.findAll();
    } 
    
    
        public void delete(Integer id) {
          this.userRepository.deleteById(id);
    }

    public Optional<Account> get(Integer id) {
          return this.userRepository.findById(id);
    }
}