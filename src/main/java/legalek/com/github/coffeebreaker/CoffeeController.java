package legalek.com.github.coffeebreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/")
    public List<Coffee> getAll() {
        return coffeeRepository.getAll();

    }
    @GetMapping("/{id}")
    public Coffee getById(@PathVariable("id") int id) {
        return coffeeRepository.getById(id);

    }

    @PostMapping("/")
    public int add(@RequestBody List<Coffee> coffees) {
       return coffeeRepository.save(coffees);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id")int id, @RequestBody Coffee updatedCoffee) {
       Coffee coffee = coffeeRepository.getById(id);

       if(coffee != null){
           coffee.setName(updatedCoffee.getName());
           coffee.setRating(updatedCoffee.getRating());

           coffeeRepository.update(coffee);

           return 200;
       } else {
           return -1;
       }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Coffee updateCoffee) {
       Coffee coffee = coffeeRepository.getById(id);
       if (coffee != null){
           if (updateCoffee.getName() != null) coffee.setName(updateCoffee.getName());
           if (updateCoffee.getRating() > 0) coffee.setRating(updateCoffee.getRating());

               coffeeRepository.update(coffee);

               return 200;

       }else {
          return  -1;
       }
    }
    @DeleteMapping("/{id}")
    public int delete (@PathVariable ("id")int id) {
        return coffeeRepository.delete(id);
    }
 }

