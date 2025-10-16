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
        IdiomaView view = new IdiomaView();
//            idioma = view.comboIdioma.getSelectedItem().toString().substring(0,2);

        idioma = view.getIdiomaSeleccionado();
        System.out.println(idioma);
        Properties properties = new Properties();
        LandingPageContent content;

        String ruta = "C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\config.properties";
        try(InputStream fis = new FileInputStream(ruta)){

            properties.load(fis);
            content = new LandingPageContent(properties.getProperty("quienes_somos." + idioma),properties.getProperty("amor_productos."+idioma), properties.getProperty("experiencia."+idioma));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
