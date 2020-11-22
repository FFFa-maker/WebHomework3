package web.homework3.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.homework3.demo.entities.Person;
import web.homework3.demo.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class LoginController {
    static HashMap<String, User> userMap = new HashMap<>();
    static{
        userMap.put("root", new User("root", "root"));
        userMap.get("root").getPersonList().add(new Person(1,"1","1","1","1","1"));
    }

    @GetMapping("/")
    public String get(Model model){
        model.addAttribute("result", "");
        return "login";
    }
    
    @PostMapping("/login")
    public String login(HttpServletRequest req,
                        Model model,
                        @RequestParam("user") String name,
                        @RequestParam("password") String password){
        if(name.equals("") || password.equals(""))
            return "login";
        if(userMap.containsKey(name) && userMap.get(name).getPassword().equals(password)) {
            req.getSession().setAttribute("user", userMap.get(name));
            return "redirect:/list";
        }
        model.addAttribute("result", "登录失败");
        return "login";
    }
}
