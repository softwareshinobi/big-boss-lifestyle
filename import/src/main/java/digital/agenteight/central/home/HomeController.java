package digital.agenteight.central.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import digital.agenteight.central.account.AccountRepository;

@Controller
public class HomeController {

    @Autowired
    AccountRepository userRepository;

    @Autowired
    public HomeController(AccountRepository userRepository) {

        System.out.println("HomeController HomeController HomeController");

        this.userRepository = userRepository;

    }

    @GetMapping("/")
    public String goToIndex(Model model) {

        model.addAttribute("info", "Great");

        return "index";

    }

    @GetMapping("/demo")
    public String goToDemo(Model model) {

        model.addAttribute("info", "Great");

        return "demo";

    }

}
