package dao;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Ingrediente;
import model.Pizza;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.XML;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAOXMLJSON implements PizzaDAO{
    String path ="C:\\Users\\alex.ariasfernandez\\IdeaProjects\\PizzeriaCosaNostra-master\\PizzeriaCosaNostra-master\\src\\main\\resources\\Carta.xml";
    String pathFinal ="C:\\Users\\alex.ariasfernandez\\IdeaProjects\\PizzeriaCosaNostra-master\\PizzeriaCosaNostra-master\\src\\main\\resources\\CartaFinal.json";
    List<Pizza> listaPizzas = new ArrayList<>();

        @Override
        public List<Pizza> readFromJSON() {
            try {
                String jsonText = Files.readString(Paths.get(pathFinal));
                JSONObject root = new JSONObject(jsonText);
                JSONObject carta = root.getJSONObject("Carta");
                JSONArray pizzas = carta.getJSONArray("Pizza");
                for (int i = 0; i < pizzas.length(); i++) {
                    JSONObject pizza = pizzas.getJSONObject(i);
                    String id = (pizza.getString("ID"));
                    String nombre = pizza.getString("Nombre");
                    double precio = pizza.getDouble("Precio");
                    int calorias = pizza.getInt("Calorias");
                    String descripcion = pizza.getString("Descripcion");
                    int tiempo = pizza.getInt("TiempoPreparacion");
                    JSONArray ingredientes =  pizza.getJSONObject("Ingredientes").getJSONArray("Ingrediente");
                    List<String> listaIngredientes = new ArrayList<>();
                    for (Object ingrediente : ingredientes) {
                        listaIngredientes.add(ingrediente.toString());
                    }
                    listaPizzas.add(new Pizza(id,nombre,descripcion,calorias,precio,tiempo,listaIngredientes));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return listaPizzas;
        }

        @Override
        public List<Pizza> readFromXML() {
            listaPizzas.clear();
            File file = new File(path);
            List<String> listaIngredientes = new ArrayList<>();
            // LEER XML
            try {
                DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbfactory.newDocumentBuilder();
                Document document = dBuilder.parse(file);
                document.getDocumentElement().normalize();

                NodeList listaNodePizzas = document.getElementsByTagName("Pizza");

                for (int i = 0; i < listaNodePizzas.getLength(); i++) {

                    Element pizza = (Element) listaNodePizzas.item(i);
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
                        Node ingrediente = nodelistIngredientes.item(j);
                        if (ingrediente.getNodeType() == Node.ELEMENT_NODE) {
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
                if (p.getId().equals(pizza.getId())) {
                    listaPizzas.remove(p);
                    añadir(pizza);
                }
            }
        }
    }



