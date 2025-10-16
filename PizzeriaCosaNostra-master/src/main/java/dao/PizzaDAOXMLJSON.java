package dao;

import model.Pizza;

import java.util.List;

public class PizzaDAOXMLJSON {
    List<Pizza> listaPizzas;
    PizzaDAO pizzaDAO = new PizzaDAO() {
        @Override
        public List<Pizza> readFromJSON() {
            return List.of();
        }

        @Override
        public List<Pizza> readFromXML() {
            return List.of();
        }

        @Override
        public void añadir(Pizza pizza) {
        listaPizzas.add(pizza);
        }

        @Override
        public void modificar(Pizza pizza) {
        for(Pizza p : listaPizzas){
            if(p.getId() == pizza.getId()){
                pizzaDAO.modificar(pizza);
            }
        }
        }
    };
}
