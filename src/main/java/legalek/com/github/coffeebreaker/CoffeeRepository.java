package legalek.com.github.coffeebreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoffeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Coffee> getAll() {
        return jdbcTemplate.query("SELECT id, name, rating FROM coffee",
                BeanPropertyRowMapper.newInstance(Coffee.class));

    }
    public Coffee getById(int id){
        Coffee coffee = jdbcTemplate.queryForObject("SELECT id, name, rating FROM coffee WHERE " +
                "id = ?", BeanPropertyRowMapper.newInstance(Coffee.class), id);
        return coffee;
    }

    public int save(List<Coffee> coffees) {
        coffees.forEach(coffee -> jdbcTemplate
                .update("INSERT INTO coffee(name, rating) VALUES(?, ?)", coffee.getName(),
                        coffee.getRating()
                ));

        return 1;
    }

    public int update(Coffee coffee) {
        return jdbcTemplate.update("UPDATE coffee SET name=?, rating=? WHERE id=?",
                coffee.getName(), coffee.getRating(), coffee.getId());
    }
    public int delete (int id) {
       return jdbcTemplate.update("DELETE FROM coffee WHERE id=?", id);
    }
}
