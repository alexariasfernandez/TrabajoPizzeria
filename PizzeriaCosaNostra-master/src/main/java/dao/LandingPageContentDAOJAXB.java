package dao;

import model.LandingPageContent;

import java.io.*;
import java.util.Properties;

public class LandingPageContentDAOJAXB implements LandingPageContentDAO{



    @Override
    public LandingPageContent RecuperarInformacion(String idioma) {
        Properties properties = new Properties();
        LandingPageContent content;

        String ruta = "C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\config.properties";
        try(InputStream fis = new FileInputStream(ruta)){

            properties.load(fis);
            System.out.println(properties.get("quienes_somos"));
            content = new LandingPageContent(properties.getProperty("quienes_somos"),properties.getProperty("amor_productos"), properties.getProperty("experiencia"));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
