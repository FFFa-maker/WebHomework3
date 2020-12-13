package web.homework3.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.homework3.demo.dao.PeopleRepository;
import web.homework3.demo.entities.Person;
import web.homework3.demo.entities.User;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class PeopleController {

    private PeopleRepository peopleRepository;

    @Autowired
    public void setPeopleRepository(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/list")
    ModelAndView list(@SessionAttribute User user,Model model){
        Iterable<Person> personlist = peopleRepository.findAll();
        System.out.println(personlist);
        model.addAttribute("personList", personlist);
        ModelAndView mv = new ModelAndView("/list");
        return mv;
    }


    @PostMapping("/add")
    String add(Model model,//以后可以排除相同的信息，今天不想改了
//               @SessionAttribute User user,
               @RequestParam("name") String name,
               @RequestParam("tele") String tele,
               @RequestParam("post") String post,
               @RequestParam("addr") String addr,
               @RequestParam("qq") String qq
               ){
//        user.getPersonList().add(new Person(user.getPersonList().size(),name, tele, post, addr, qq));
        Person person = new Person(name, tele, post, addr, qq);
        peopleRepository.save(person);
        return "redirect:/list";
    }

    @GetMapping("/add")
    String getAdd(Model model){
        model.addAttribute("result", "");
        return "add";
    }



    @PostMapping("/change")
    String change(Model model,
                  Long id,
               @SessionAttribute User user,
               @RequestParam("name") String name,
               @RequestParam("tele") String tele,
               @RequestParam("post") String post,
               @RequestParam("addr") String addr,
               @RequestParam("qq") String qq
    ){
//        model.addAttribute("user", user.getPersonList().get(id-1));
//        Person person = user.getPersonList().get(id-1);
        Person person = peopleRepository.findById(id).get();
        person.setName(name);
        person.setTele(tele);
        person.setAddr(addr);
        person.setPost(post);
        person.setQq(qq);
        peopleRepository.save(person);
        return "redirect:/list";
    }

    @GetMapping("/change")
    ModelAndView getChange(Model model, long id, @SessionAttribute User user){
        model.addAttribute("result", "");
        model.addAttribute("user", peopleRepository.findById(id).get());
        return new ModelAndView("/change").addObject("id", id);
    }

    @GetMapping("/del")
    ModelAndView getDel(long id, @SessionAttribute User user){
//        user.getPersonList().remove(id-1);
        peopleRepository.deleteById(id);
        return new ModelAndView("redirect:/list");
    }

    @GetMapping("/ifExisted")
    @ResponseBody
    public String ifExisted(@RequestParam String tele){
        Iterable<Person> people = peopleRepository.findAll();
        for (Person person : people) {
            if (person.getTele().equals(tele))

                return "该电话号码已经存在！！";
        }
        return "";
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
