package Book.Spring.Controller;
import Book.Spring.model.User;
import Book.Spring.Service.SecurityService;
import Book.Spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;


    @GetMapping("/Singin")
    public String registration(Model model) {

        model.addAttribute("userForm", new User());

        return "Singin";

    }

    @PostMapping("/Singin")
    public String registration(@ModelAttribute("userForm") User userForm) {

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/main";


    }

    @GetMapping("/login")
    public String login(Model model) {

        if (securityService.isAuthenticated()) {
            return "main";
        }

        return "login";
    }

    @GetMapping("/main")
    public String main(Model model) {

        return "main";

    }


}