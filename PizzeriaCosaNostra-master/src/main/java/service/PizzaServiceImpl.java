package service;

import dao.PizzaDAO;
import dao.PizzaDAOXMLJSON;
import model.Pizza;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
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
    File file = new File("C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\Carta.xml");

    @Override
    public List<Pizza> listadoPizzas() throws JAXBException {
        return dao.readFromXML();
    }

    @Override
    public void actualizarPizza(Pizza p) throws JAXBException {
        List<Pizza> pizzas = listadoPizzas();
        boolean encontrado = false;

        // Actualizar o añadir en la lista en memoria
        for (int i = 0; i < pizzas.size(); i++) {
            if (pizzas.get(i).getId().equals(p.getId())) {
                pizzas.set(i, p);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            pizzas.add(p);
        }
        // ESCRIBIR XML
        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
            Document document = dBuilder.newDocument();

            // Crear elemento raíz
            Element root = document.createElement("Pizzas");
            document.appendChild(root);

            for (Pizza pizza : pizzas) {
                Element pizzaElem = document.createElement("Pizza");
                pizzaElem.setAttribute("ID", pizza.getId());

                Element nombre = document.createElement("Nombre");
                nombre.setTextContent(pizza.getNombre());
                pizzaElem.appendChild(nombre);

                Element descripcion = document.createElement("Descripcion");
                descripcion.setTextContent(pizza.getDescripcion());
                pizzaElem.appendChild(descripcion);

                Element calorias = document.createElement("Calorias");
                calorias.setTextContent(String.valueOf(pizza.getCalorias()));
                pizzaElem.appendChild(calorias);

                Element precio = document.createElement("Precio");
                precio.setTextContent(String.valueOf(pizza.getPrecio()));
                pizzaElem.appendChild(precio);

                Element tiempoPreparacion = document.createElement("TiempoPreparacion");
                tiempoPreparacion.setTextContent(String.valueOf(pizza.getTiempoPreparacion()));
                pizzaElem.appendChild(tiempoPreparacion);

                Element ingredientes = document.createElement("Ingredientes");
                for (String ingr : pizza.getIngredientes()) {
                    Element ingrediente = document.createElement("Ingrediente");
                    ingrediente.setTextContent(ingr);
                    ingredientes.appendChild(ingrediente);
                }
                pizzaElem.appendChild(ingredientes);

                root.appendChild(pizzaElem);
            }

            // Guardar en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
