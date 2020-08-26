package edu.northeastern.cs5200.controller;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonAllController {
    @Autowired
    private PersonService personService;

    @GetMapping("/personall")
    public String personall(Model model) {
        List<Person> person = this.personService.selectPersonByName();
        model.addAttribute("person", person);
        return "personall";
    }


    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        personService.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/personall");
        return mav;
    }

    @RequestMapping("Add1")
    public ModelAndView Add1() {
        return new ModelAndView("add");
    }

    @RequestMapping("AddPerson")
    public ModelAndView AddUser(Person person) {
        personService.add(person);
        ModelAndView mav = new ModelAndView("redirect:/personall");
        return mav;
    }
}
