package digital.agenteight.bigbosslifestyle.auth;

import digital.agenteight.bigbosslifestyle.account.AccountService;
import digital.agenteight.bigbosslifestyle.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {

        this.accountService = accountService;

    }

    @GetMapping("register")
    public String renderRegisterForm(Model model) {

        model.addAttribute("myUser", new Account());

        return "auth/register";

    }

    @PostMapping("register")
    public String processRegisterForm(@ModelAttribute Account myUser, Model model) {

        System.out.println(">> myUser: " + myUser);

        Account savedUser = this.accountService.saveUser(myUser);

        if (savedUser != null) {

            model.addAttribute("info", "Signup success!");

            return "auth/login";

        } else {

            model.addAttribute("error", "Signup Failed");

            return "auth/register";

        }

    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("myUser", new Account());

        model.addAttribute("error", "");

        return "auth/login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account myUser, Model model, HttpServletRequest request) {

        System.out.println(">> myUser: " + myUser);

        Account currentUser = this.accountService.authenticateUser(myUser);

        if (currentUser != null) {

            model.addAttribute("myUser", currentUser);

            request.getSession(true).setAttribute("currentUser", currentUser);

            return "home/home";

        } else {

            model.addAttribute("error", "Invalid credentials. Try Again.");

            return "auth/login";

        }

    }

}
