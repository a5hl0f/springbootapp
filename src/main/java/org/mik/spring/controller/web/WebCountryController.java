package org.mik.spring.controller.web;

import org.mik.spring.entity.Country;
import org.mik.spring.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/country")
public class WebCountryController {

    private CountryService countryService;

    public WebCountryController(@Autowired CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public String findById(Model model) {
        model.addAttribute("countries", countryService.getAllCountries());
        return "countries";
    }

    @GetMapping("/updateform/{id}")
    public String showUpdate(@PathVariable(value = "id") long id, Model model) {
        Country c=this.countryService.getById(id);
        model.addAttribute("country", c);
        return "updatecountry";
    }

    @PostMapping("/add")
    public String addCountry(@Valid Country country, BindingResult result, Model model) {
        if (result.hasErrors())
            return "newcountry";

        this.countryService.saveCountry(country);
        return "countries";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        Country c=countryService.getById(id);
        if(c!=null) {
            model.addAttribute("country", c);
            return "updatecountry";
        }
        throw new IllegalArgumentException("Invalid country id:"+id);
    }

    @PostMapping("/update/{id}")
    public String updateCountry(@PathVariable("id") long id, @Valid Country country, BindingResult result, Model model) {
        country.setId(id);
        if (result.hasErrors())
            return "updatecountry";

        countryService.saveCountry(country);
        return "redirect:/country/all";
    }

    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable("id") long id) {
        Country c=this.countryService.getById(id);
        if (c!=null) {
            this.countryService.delete(c);
            return "redirect:/country/all";
        }
        throw new IllegalArgumentException("Invalid country id:"+id);
    }
}
