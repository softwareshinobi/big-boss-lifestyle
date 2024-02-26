package digital.agenteight.bigbosslifestyle.home;

import digital.agenteight.bigbosslifestyle.experience.BudgetRepository;
import digital.agenteight.bigbosslifestyle.user.MyUserRepository;
import digital.agenteight.bigbosslifestyle.transaction.TransactionRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@Slf4j
public class HomeController {

    @Autowired
    MyUserRepository userRepository;
    BudgetRepository budgetRepository;
    TransactionRepository transactionRepository;

    @Autowired
    public HomeController(MyUserRepository userRepository, BudgetRepository budgetRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
        this.transactionRepository = transactionRepository;
    }


    @GetMapping("/")
    public String goToIndex(Model model) {
        model.addAttribute("info", "Great");
        return "index";

    }



}


