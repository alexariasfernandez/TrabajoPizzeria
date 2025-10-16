package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "carta")

public class Carta implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "pizza")
    private List<Pizza> pizzas = new ArrayList<>();

    public Carta() { }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}

