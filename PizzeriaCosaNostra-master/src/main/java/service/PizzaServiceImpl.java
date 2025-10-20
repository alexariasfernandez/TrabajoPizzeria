package service;

import dao.PizzaDAO;
import dao.PizzaDAOXMLJSON;
import model.Pizza;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class PizzaServiceImpl implements PizzaService{
PizzaDAO dao = new PizzaDAOXMLJSON();

    @Override
    public List<Pizza> listadoPizzas() throws JAXBException {
        return dao.readFromXML();
    }

    @Override
    public void actualizarPizza(Pizza p) throws JAXBException {
        File file = new File("C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\Carta.xml");
        List<Pizza> pizzas = dao.readFromXML();
        boolean encontrado = false;
        for(int i = 0;i<pizzas.size();i++){
            if(pizzas.get(i).getId().equalsIgnoreCase(p.getId())){
                pizzas.set(i, p);
                encontrado = true;
                break;
            }
        }
        if(!encontrado){
            pizzas.add(p);
        }
        try{
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder= dbfactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);
            document.getDocumentElement().normalize();

            Element pizzaNueva = document.createElement("Pizza");
            pizzaNueva.setAttribute("ID",p.getId());

            Element nombre = document.createElement("Nombre");
            nombre.setTextContent(p.getNombre());
            pizzaNueva.appendChild(nombre);

            Element descripcion = document.createElement("Descripcion");
            descripcion.setTextContent(p.getDescripcion());
            pizzaNueva.appendChild(descripcion);

            Element calorias = document.createElement("Calorias");
            calorias.setTextContent(String.valueOf(p.getCalorias()));
            pizzaNueva.appendChild(calorias);

            Element precio = document.createElement("Precio");
            precio.setTextContent(String.valueOf(p.getPrecio()));
            pizzaNueva.appendChild(precio);

            Element tiempoPreparacion = document.createElement("TiempoPreparacion");
            tiempoPreparacion.setTextContent(String.valueOf(p.getTiempoPreparacion()));
            pizzaNueva.appendChild(tiempoPreparacion);

            Element ingredientes = document.createElement("Ingredientes");

            List<String> listaingredientes = p.getIngredientes();
            for(String id : listaingredientes){
                Element ingrediente = document.createElement("Ingrediente");
                ingrediente.setTextContent(id);
                ingredientes.appendChild(ingrediente);
            }
            pizzaNueva.appendChild(ingredientes);

            Node root = document.getDocumentElement();
            root.appendChild(pizzaNueva);

            TransformerFactory TFactory = TransformerFactory.newInstance();
            Transformer transformer = TFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source,result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
