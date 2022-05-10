package org.mik.spring.controller.web;

import org.mik.spring.entity.Country;
import org.mik.spring.entity.Person;
import org.mik.spring.service.CountryService;
import org.mik.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/person")
public class WebPersonController {

    private CountryService countryService;
    private PersonService personService;

    public WebPersonController(@Autowired CountryService countryService,
                               @Autowired PersonService personService) {
        this.countryService = countryService;
        this.personService = personService;
    }

    @GetMapping("/all")
    public String findAll(Model model,
                          @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size) {
        int currentPage=page.orElse(0);
        int pageSize=size.orElse(5);
        Page<Person> result=personService.getPaginated(PageRequest.of(currentPage, pageSize));
        model.addAttribute("persons", result);
        int totalPages=result.getTotalPages();
        if (totalPages>0) {
            List<Integer> pageNumbers= IntStream.rangeClosed(0, totalPages-1)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "persons";
    }

    //http://localhost:8088/person/all?page=1&size=2

    @GetMapping("/updateform/{id}")
    public String showUpdate(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("countries", this.countryService.getAllCountries());
        Person p=this.personService.getById(id);
        model.addAttribute("person", p);
        return "updateperson";
    }

    @GetMapping("/newform")
    public String showNew(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("countries", this.countryService.getAllCountries());
        return "newperson";
    }

    @PostMapping("/add")
    public String addPerson(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors())
            return "newperson";

        this.personService.savePerson(person);
        return "persons";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        Person p=personService.getById(id);
        model.addAttribute("countries", this.countryService.getAllCountries());
        if(p!=null) {
            model.addAttribute("person", p);
            return "updateperson";
        }
        throw new IllegalArgumentException("Invalid person id:"+id);
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") long id, @Valid Person person, BindingResult result, Model model) {
        person.setId(id);
        if (result.hasErrors())
            return "updateperson";

        personService.savePerson(person);
        return "redirect:/person/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") long id) {
        Person p=this.personService.getById(id);
        if (p!=null) {
            this.personService.delete(p);
            return "redirect:/person/all";
        }
        throw new IllegalArgumentException("Invalid person id:"+id);
    }

}
