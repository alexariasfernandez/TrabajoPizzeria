package dao;

import model.Ingrediente;
import service.IngredienteService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAOImpl implements IngredienteDAO {
    String path ="C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\ingredientes.dat";
    List <Ingrediente> listaingredientes = new ArrayList<>();

    @Override
    public List<Ingrediente> getAll() {
        try (ObjectInputStream obxecto = new ObjectInputStream(new FileInputStream(path))){
            listaingredientes = (List<Ingrediente>) obxecto.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaingredientes;
    }
}
