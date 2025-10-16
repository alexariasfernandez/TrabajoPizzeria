package dao;

import model.Local;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LocalDAOImpl implements LocalDAO{
    String path = "C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\locales.csv";

    public List<Local> LecturaCSV() {


        List<Local> listalocales = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

         String linea;

         int contador=0;
         while ((linea = br.readLine()) != null){
             if(contador ==0){
                 contador++;
                 continue;
             }
             String[] palabra = linea.split(",");
             listalocales.add(new Local(palabra[0],palabra[1],palabra[2],palabra[3],palabra[4],palabra[5],palabra[6],Integer.parseInt(palabra[7])));

         }

//         for (Local local : listalocales){
//             System.out.println(local);
//         }
            br.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
return  listalocales;
    }
}
