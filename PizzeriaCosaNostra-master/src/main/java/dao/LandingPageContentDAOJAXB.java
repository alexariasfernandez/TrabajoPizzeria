package dao;

import vista.IdiomaView;
import vista.LandingPageView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class LandingPageContentDAOJAXB implements LandingPageContentDAO{
private Properties properties;

    public LandingPageContentDAOJAXB(String idioma) {
        properties = new Properties();
        RecuperarInformacion(idioma);
    }

    @Override
    public void RecuperarInformacion(String idioma) {
        String ruta = "./src/main/resources/config.properties";
        try(FileInputStream fis = new FileInputStream(ruta)){

            properties.load(new InputStreamReader(fis, StandardCharsets.UTF_8));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getInfo(String clave, String idioma){
        String key = clave + "." + idioma.toUpperCase();
        return properties.getProperty(key,"Información no disponible");
    }
}
