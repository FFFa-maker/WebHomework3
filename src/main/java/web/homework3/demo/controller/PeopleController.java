package web.homework3.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.homework3.demo.entities.Person;
import web.homework3.demo.entities.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;

@Controller
public class PeopleController {
    @GetMapping("/list")
    ModelAndView list(@SessionAttribute User user,Model model){
        model.addAttribute("personList", user.getPersonList());
        ModelAndView mv = new ModelAndView("/list");
        return mv;
    }

    @PostMapping("/add")
    String add(Model model,//以后可以排除相同的信息，今天不想改了
               @SessionAttribute User user,
               @RequestParam("name") String name,
               @RequestParam("tele") String tele,
               @RequestParam("post") String post,
               @RequestParam("addr") String addr,
               @RequestParam("qq") String qq
               ){
        user.getPersonList().add(new Person(user.getPersonList().size(),name, tele, post, addr, qq));
        return "redirect:/list";
    }
    @GetMapping("/add")
    String getAdd(Model model){
        model.addAttribute("result", "");
        return "add";
    }
    @PostMapping("/change")
    String change(Model model,
                  int id,
               @SessionAttribute User user,
               @RequestParam("name") String name,
               @RequestParam("tele") String tele,
               @RequestParam("post") String post,
               @RequestParam("addr") String addr,
               @RequestParam("qq") String qq
    ){
        model.addAttribute("user", user.getPersonList().get(id-1));
        Person person = user.getPersonList().get(id-1);
        person.setName(name);
        person.setTele(tele);
        person.setAddr(addr);
        person.setPost(post);
        person.setQq(qq);
        return "redirect:/list";
    }

    @GetMapping("/change")
    ModelAndView getChange(Model model, int id, @SessionAttribute User user){
        model.addAttribute("result", "");
        model.addAttribute("user", user.getPersonList().get(id-1));
        return new ModelAndView("/change").addObject("id", id);
    }

    @GetMapping("del")
    ModelAndView getDel(int id, @SessionAttribute User user){
        user.getPersonList().remove(id-1);
        return new ModelAndView("redirect:/list");
    }

//    boolean match(String tele, String post, String qq){
//        String telereg = "/^1[3-8]\\d{9}$/";
//        String postreg = "/^([a-zA-Z]|[0-9])(\\w|-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$/";
//        String qqreg = "/^[1-9]\\d{4,9}$/";
//        if(tele.matches(telereg) && (post.equals("")||post.matches(postreg)) && (qq.equals("") || qq.matches(qqreg)) )
//            return true;
//        return false;
//    }
}
