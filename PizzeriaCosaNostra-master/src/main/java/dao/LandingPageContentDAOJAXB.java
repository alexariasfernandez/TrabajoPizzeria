package dao;

import model.LandingPageContent;
import vista.IdiomaView;
import vista.LandingPageView;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class LandingPageContentDAOJAXB implements LandingPageContentDAO{


    @Override
    public LandingPageContent RecuperarInformacion(String idioma) {
        String ruta = "C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\config.properties";
        Properties properties = new Properties();
        LandingPageContent content;
        IdiomaView view = new IdiomaView();
try(InputStream fis = new FileInputStream(ruta)){

        if(idioma.equals("ES")) {
            properties.load(fis);
            content = new LandingPageContent(properties.getProperty("quienes_somos." + idioma),properties.getProperty("amor_productos."+idioma), properties.getProperty("experiencia."+idioma));
            return content;
        }
        if(idioma.equals("EN")) {
            properties.load(fis);
            content = new LandingPageContent(properties.getProperty("quienes_somos." + idioma),properties.getProperty("amor_productos."+idioma), properties.getProperty("experiencia."+idioma));
            return content;
        }
        if(idioma.equals("GAL")) {
            properties.load(fis);
            content = new LandingPageContent(properties.getProperty("quienes_somos." + idioma),properties.getProperty("amor_productos."+idioma), properties.getProperty("experiencia."+idioma));
            return content;
        }
        System.out.println(idioma);
        return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
