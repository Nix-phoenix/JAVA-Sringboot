package Springboot.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Springboot.Service.CityService;
import Springboot.entities.City;

@RestController
public class HomeController {
    @Autowired
    CityService cityService;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/getcities")
    public List<City> getCities() {
        return cityService.getCities();
    }

    @PostMapping("/addcity")
    public City SaveCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }
}
