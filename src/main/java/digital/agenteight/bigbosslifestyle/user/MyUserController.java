package digital.agenteight.bigbosslifestyle.user;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/user")
public class MyUserController {

    @Autowired
    MyUserService userService;

    @Autowired
    public MyUserController(MyUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String displayUserManageScreen(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        //todo make a function
        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        }

        MyUser currentUser = (MyUser) obj;
        model.addAttribute("myUser", currentUser);

        List<MyUser> MyUserList = this.userService.getAll();
        model.addAttribute("myUserList", MyUserList);

        return "user/manage";

    }

    @GetMapping("/create-user")
    public String displayUserCreateScreen(Model model, HttpServletRequest request) {

        model.addAttribute("myUser", new MyUser());

        return "user/create";

    }

    @PostMapping("/create-user")
    public String processUserCreateForm(@ModelAttribute MyUser myUser, Model model, HttpServletRequest request) {

        System.out.println(" enter > processUserCreateForm / " + myUser);

        MyUser savedUser = this.userService.saveUser(myUser);

        if (savedUser != null) {

            model.addAttribute("info", "Signup success!");

        } else {

            model.addAttribute("error", "Signup Failed");

        }

            return displayUserManageScreen(model, request);

    }
    
  @GetMapping("/signupForm")
    public String goTdddoIndex(Model model) {
        model.addAttribute("myUser", new MyUser());
        return "user/register";

    }

    @PostMapping("/signupUser")
    public String signupUser(@ModelAttribute MyUser myUser, Model model) {
        System.out.println(">> myUser: " + myUser);
        MyUser savedUser = this.userService.saveUser(myUser);
        if (savedUser != null) {
            model.addAttribute("info", "Signup success!");
            return "index";
        } else {
            model.addAttribute("error", "Signup Failed");
            return "user/register";
        }
    }
      
    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("myUser", new MyUser());
        model.addAttribute("error", "");
        return "user/login";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute MyUser myUser, Model model, HttpServletRequest request) {
        System.out.println(">> myUser: " + myUser);
        MyUser currentUser = this.userService.authenticateUser(myUser);
        if (currentUser != null) {
            model.addAttribute("myUser", currentUser);
            request.getSession(true).setAttribute("currentUser", currentUser);
            return "home/landing";
        } else {
            model.addAttribute("error", "Invalid credentials. Try Again.");
            return "user/login";
        }
    }

    @GetMapping("/userHome")
    public String userHome(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");
        if (obj == null) {
            model.addAttribute("error", "Login session expired");
            return "redirect:/user/loginForm";
        }

        MyUser currentUser = (MyUser) obj;
        model.addAttribute("myUser", currentUser);
        return "home/landing";

    }

  


    @GetMapping("/edit-user")
    public String editUser(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        } else {

            MyUser authenticatedUser = (MyUser) currentUser;

            model.addAttribute("myUser", authenticatedUser);

        }

        Optional<MyUser> userToEdit = this.userService.get(id);

        if (userToEdit.isPresent()) {

            model.addAttribute("userToEdit", userToEdit.get());

            return "user/edit";

        } else {

            model.addAttribute("error", "no user account with id / " + id);

            return displayUserManageScreen(model, request);

        }

    }
    
    @PostMapping("/edit-user")
    public String processUserEditForm(@ModelAttribute MyUser myUser, Model model, HttpServletRequest request) {

        System.out.println(" enter > processUserEditForm / " + myUser);

                System.out.println(" myUser / " + myUser);

        MyUser savedUser = this.userService.saveUser(myUser);

                System.out.println(" savedUser / " + myUser);

        if (savedUser != null) {

            model.addAttribute("info", "User updated!");

        } else {

            model.addAttribute("error", "User edit Failed");

        }

            return displayUserManageScreen(model, request);

    }
    
    @GetMapping("/delete-user")
    public String delete(@RequestParam Integer id, Model model, HttpServletRequest request) {

        Object currentUser = request.getSession().getAttribute("currentUser");

        if (currentUser == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/user/loginForm";

        } else {

            MyUser authenticatedUser = (MyUser) currentUser;

            model.addAttribute("myUser", authenticatedUser);

        }

        this.userService.delete(id);

            return displayUserManageScreen(model, request);

    }

}
