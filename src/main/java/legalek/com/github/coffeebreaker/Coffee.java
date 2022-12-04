package legalek.com.github.coffeebreaker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    private int id;
    private String name;
    private int rating;
}
