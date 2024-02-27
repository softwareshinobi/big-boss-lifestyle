package digital.agenteight.bigbosslifestyle.account;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("user")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {

        this.accountService = accountService;

    }

    @GetMapping("/userHome")
    public String userHome(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Account currentUser = (Account) obj;

        model.addAttribute("myUser", currentUser);

        return "home/landing";

    }

    @GetMapping("/")
    public String displayUserManageScreen(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        //todo make a function
        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        }

        Account currentUser = (Account) obj;
        model.addAttribute("myUser", currentUser);

        List<Account> MyUserList = this.accountService.getAll();
        model.addAttribute("myUserList", MyUserList);

        return "user/manage";

    }

    @GetMapping("/create")
    public String displayUserCreateScreen(Model model, HttpServletRequest request) {

        model.addAttribute("myUser", new Account());

        return "user/create";

    }

    @PostMapping("/create")
    public String processUserCreateForm(@ModelAttribute Account myUser, Model model, HttpServletRequest request) {

        System.out.println(" enter > processUserCreateForm / " + myUser);

        Account savedUser = this.accountService.saveUser(myUser);

        if (savedUser != null) {

            model.addAttribute("info", "Signup success!");

        } else {

            model.addAttribute("error", "Signup Failed");

        }

        return displayUserManageScreen(model, request);

    }

    @GetMapping("/edit")
    public String editUser(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        } else {

            Account authenticatedUser = (Account) currentUser;

            model.addAttribute("myUser", authenticatedUser);

        }

        Optional<Account> userToEdit = this.accountService.get(id);

        if (userToEdit.isPresent()) {

            model.addAttribute("userToEdit", userToEdit.get());

            return "user/edit";

        } else {

            model.addAttribute("error", "no user account with id / " + id);

            return displayUserManageScreen(model, request);

        }

    }

    @PostMapping("/edit")
    public String processUserEditForm(@ModelAttribute Account myUser, Model model, HttpServletRequest request) {

        System.out.println(" enter > processUserEditForm / " + myUser);

        System.out.println(" myUser / " + myUser);

        Account savedUser = this.accountService.saveUser(myUser);

        System.out.println(" savedUser / " + myUser);

        if (savedUser != null) {

            model.addAttribute("info", "User updated!");

        } else {

            model.addAttribute("error", "User edit Failed");

        }

        return displayUserManageScreen(model, request);

    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        } else {

            Account authenticatedUser = (Account) currentUser;

            model.addAttribute("myUser", authenticatedUser);

        }

        this.accountService.delete(id);

        return displayUserManageScreen(model, request);

    }

}
