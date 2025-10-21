package service;

import dao.IngredienteDAO;
import dao.IngredienteDAOImpl;
import model.Ingrediente;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class IngredienteServiceImpl implements IngredienteService{
    IngredienteDAO dao = new IngredienteDAOImpl();
    String path ="C:\\Users\\xoel.lagohermida\\ProyectoPizza\\PizzeriaCosaNostra-master\\src\\main\\resources\\ingredientes.dat";
    @Override
    public List<Ingrediente> listadoIngredientes() {
        return dao.getAll();
    }

    @Override
    public void actualizarIngrediente(Ingrediente ing){
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
