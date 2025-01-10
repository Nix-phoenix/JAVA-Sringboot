package Springboot.reposi;

import org.springframework.data.jpa.repository.JpaRepository;
import Springboot.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityname(String cityname);
}
