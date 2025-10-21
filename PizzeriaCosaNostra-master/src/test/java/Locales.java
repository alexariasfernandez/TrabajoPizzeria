import dao.*;
import model.Ingrediente;
import model.Local;
import model.Pizza;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class Locales {
    @Test
    public void pruebaLocales(){
        LocalDAO dao = new LocalDAOImpl();
        Local l = new Local("Id01","PEPITO", "PEPITO","15002","A Coruña","A Coruña", "981111111",1);
        Local l2 = new Local("Id02","PEPITO", "PEPITO","15002","A Coruña","A Coruña", "981111111",1);

        assertFalse(l.getId().equals(l2.getId()));
        assertNotNull(dao.getListaLocales());
        assertNotNull(dao.LecturaCSV());

    }
    @Test
    public void pruebaPizzas(){
        PizzaDAO dao = new PizzaDAOXMLJSON();
        List<String> listaingredientes = new ArrayList<>();
        listaingredientes.add("In1");
        listaingredientes.add("In2");
        Pizza p = new Pizza("Id01","Jamon", "PEPITO",15002,10.0,1,listaingredientes );
        listaingredientes.remove("In2");
        listaingredientes.add("In3");
        Pizza p2 = new Pizza("Id02","Pollo", "PEPITO",15002,12.0,30, listaingredientes);

        assertFalse(p.getId().equals(p2.getId()));
        assertNotNull(dao.readFromJSON());
        assertNotNull(dao.readFromXML());


    }
    @Test
    public void pruebaIngredientes() throws ClassNotFoundException {
        IngredienteDAO dao = new IngredienteDAOImpl();
        Ingrediente l = new Ingrediente("Id01","PEPITO", "PEPITO","15002",false,true, false,true);
        Ingrediente l2 = new Ingrediente("Id02","PEPITO", "PEPITO","15002",true,false, false,false);

        assertFalse(l.getId().equals(l2.getId()));
        assertNotNull(dao.getAll());
    }
}
