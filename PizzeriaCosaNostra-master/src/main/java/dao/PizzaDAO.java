package dao;

import model.Pizza;
import service.PizzaService;

import java.util.List;

public interface  PizzaDAO {
    public List<Pizza> readFromJSON();
    public List<Pizza> readFromXML();
    public void añadir(Pizza pizza);
    public void modificar(Pizza pizza);
}
