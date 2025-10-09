package dao;

import vista.IdiomaView;
import vista.LandingPageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPageContentDAOJAXB implements LandingPageContentDAO{
IdiomaView idiomaView;
LandingPageView landpv;
String idiomaSeleccionado = idiomaView.getIdiomaSeleccionado();
    @Override
    public void RecuperarInformacion() {
        Properties prop = new Properties();
        String ruta = "src\\main\\resources\\config.properties";
        try(FileInputStream fis = new FileInputStream(ruta)) {
        prop.load(fis);
        for (String props: prop.stringPropertyNames()) {
            switch (idiomaSeleccionado) {
                case "EN" -> landpv.mostrarContenido(prop.getProperty("quienes_somos.EN").toString(),prop.getProperty("amor_productos.EN").toString(),prop.getProperty("experiencia.EN").toString());
                case "ES" -> landpv.mostrarContenido(prop.getProperty("quienes_somos.ES").toString(),prop.getProperty("amor_productos.ES").toString(),prop.getProperty("experiencia.ES").toString());
                case "GAL" -> landpv.mostrarContenido(prop.getProperty("quienes_somos.GAL").toString(),prop.getProperty("amor_productos.GAL").toString(),prop.getProperty("experiencia.GAL").toString());
            }
        }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }
}
