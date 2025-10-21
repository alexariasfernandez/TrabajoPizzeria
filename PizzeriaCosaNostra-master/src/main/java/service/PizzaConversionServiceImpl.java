package service;

import dao.PizzaDAO;
import dao.PizzaDAOXMLJSON;
import org.json.JSONObject;
import org.json.XML;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PizzaConversionServiceImpl implements PizzaConversionService {
    PizzaDAO dao = new PizzaDAOXMLJSON();
    String path ="C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\Carta.xml";
    String pathFinal ="C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\CartaFinal.json";

    File file = new File("C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\CartaFinal.json");
    @Override
    public boolean generarFicheroPizzas() {
        if (file.exists()) {
            file.delete();

        }
            // crear el fichero json
            try {
                String XMLContent  = Files.readString(Paths.get(path));
                JSONObject jsonObject = XML.toJSONObject(XMLContent);
                String prettyJSON = jsonObject.toString(4);
                try(FileWriter fw = new FileWriter(pathFinal)){
                    fw.write(prettyJSON);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(file.exists()) {
                return true;
            } else {
                return false;
            }

        }

    }
