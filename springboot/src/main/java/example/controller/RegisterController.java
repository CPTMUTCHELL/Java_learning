package example.controller;

import example.entity.Role;
import example.entity.User;
import example.repository.RoleRepository;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Controller
public class RegisterController {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("user", new User());
        return "views/register.html";
    }
    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model,
                               @RequestParam("login") String login, RedirectAttributes ra){
        if (bindingResult.hasErrors()) return "views/register.html";
        if (userRepository.findByName(user.getLogin())!=null) {
            model.addAttribute("exist", true);
            return "views/register.html";
        }
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPass(encoder.encode(user.getPass()));
        Role role=roleRepository.getOne(9);
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        ra.addFlashAttribute("success",true);
        ra.addFlashAttribute("login","Registration successful, "+login);
        return "redirect:/login";
    }



}
