package dao;

import model.Ingrediente;
import service.IngredienteService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAOImpl implements IngredienteService {
    String path ="C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\ingredientes.dat";
    List <Ingrediente> listaingredientes = new ArrayList<>();
    @Override
    public List<Ingrediente> listadoIngredientes() throws ClassNotFoundException {
        try (ObjectInputStream obxecto = new ObjectInputStream(new FileInputStream(path))){
            listaingredientes = (List<Ingrediente>) obxecto.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaingredientes;
    }

    @Override
    public void actualizarIngrediente(Ingrediente ing) throws IOException, ClassNotFoundException {
        List<Ingrediente> ingredientes = listadoIngredientes();

        boolean encontrado = false;
        for (int i = 0; i < ingredientes.size(); i++) {
            if (ingredientes.get(i).getId().equals(ing.getId())) {
                ingredientes.set(i, ing);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            ingredientes.add(ing);
        }

        // Guarda la lista completa actualizada
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(ingredientes);
            System.out.println("Archivo actualizado correctamente.");
        }
    }
}
