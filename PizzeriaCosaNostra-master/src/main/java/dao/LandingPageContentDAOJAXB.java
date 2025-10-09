package dao;

import vista.IdiomaView;
import vista.LandingPageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LandingPageContentDAOJAXB implements LandingPageContentDAO{
IdiomaView idiomaView;
LandingPageView landpv;
String idiomaSeleccionado;
String quienesomos;
String nuestrapasion;
String experiencia;

    @Override
    public void RecuperarInformacion() {
        idiomaSeleccionado= idiomaView.getIdiomaSeleccionado();
        Properties prop = new Properties();
        String ruta = "src\\main\\resources\\config.properties";
        try(FileInputStream fis = new FileInputStream(ruta)) {
        prop.load(fis);
            switch (idiomaSeleccionado) {
                case "EN" -> {
                    quienesomos= prop.getProperty("quienes_somos.EN").toString();
                    nuestrapasion=prop.getProperty("amor_productos.EN").toString();
                    experiencia =prop.getProperty("experiencia.EN").toString();
                }
                case "ES" -> {
                    quienesomos =prop.getProperty("quienes_somos.ES").toString();
                    nuestrapasion=prop.getProperty("amor_productos.ES").toString();
                    experiencia=prop.getProperty("experiencia.ES").toString();
                }
                case "GAL" -> {
                    quienesomos=prop.getProperty("quienes_somos.GAL").toString();
                    nuestrapasion=prop.getProperty("amor_productos.GAL").toString();
                    experiencia=prop.getProperty("experiencia.GAL").toString();
                }
            }

        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    public String getQuienesomos() {
        return quienesomos;
    }

    public void setQuienesomos(String quienesomos) {
        this.quienesomos = quienesomos;
    }

    public String getNuestrapasion() {
        return nuestrapasion;
    }

    public void setNuestrapasion(String nuestrapasion) {
        this.nuestrapasion = nuestrapasion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
