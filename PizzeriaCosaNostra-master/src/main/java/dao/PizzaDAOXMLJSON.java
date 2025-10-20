package dao;


import model.Pizza;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

public class PizzaDAOXMLJSON implements PizzaDAO{
    String path ="C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\Carta.xml";
    List<Pizza> listaPizzas = new ArrayList<>();
        @Override
        public List<Pizza> readFromJSON() {
            return List.of();
        }

        @Override
        public List<Pizza> readFromXML() {
            listaPizzas.clear();
            File file = new File(path);
            List<String> listaIngredientes = new ArrayList<>();
            try {
                DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
                Document document = dBuilder.parse(file);
                document.getDocumentElement().normalize();

                NodeList listaPizzas = document.getElementsByTagName("Pizza");
                for (int i = 0; i < listaPizzas.getLength(); i++) {
                    Element pizza = (Element) listaPizzas.item(i);
                    Attr id = null;
                    if (pizza.hasAttribute("ID")) {
                        id = pizza.getAttributeNode("ID");
                    }
                    Node nombre = pizza.getElementsByTagName("Nombre").item(0);
                    Node descripcion = pizza.getElementsByTagName("Descripcion").item(0);
                    Node calorias = pizza.getElementsByTagName("Calorias").item(0);
                    Node precio = pizza.getElementsByTagName("Precio").item(0);
                    Node tiempoPreparacion = pizza.getElementsByTagName("TiempoPreparacion").item(0);

                    NodeList nodelistIngredientes = pizza.getElementsByTagName("Ingredientes").item(0).getChildNodes();
                    for (int j = 0; j < nodelistIngredientes.getLength(); j++) {
                        Node ingredientes = nodelistIngredientes.item(j);
                        if(ingredientes.getNodeType() == Node.ELEMENT_NODE){
                            Element ingrediente = (Element) nodelistIngredientes;
                            listaIngredientes.add(ingrediente.getTextContent());
                        }
                    }
                    añadir(new Pizza(id.getNodeValue(), nombre.getTextContent(), descripcion.getTextContent(), Integer.parseInt(calorias.getTextContent()), Double.parseDouble(precio.getTextContent()), Integer.parseInt(tiempoPreparacion.getTextContent()), listaIngredientes));
                }


            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
            return listaPizzas;
        }


        @Override
        public void añadir(Pizza pizza) {
            listaPizzas.add(pizza);
        }

        @Override
        public void modificar(Pizza pizza) {
            for (Pizza p : listaPizzas) {
                if (p.getId() == pizza.getId()) {
                    modificar(pizza);

                }
            }
        }
    }



