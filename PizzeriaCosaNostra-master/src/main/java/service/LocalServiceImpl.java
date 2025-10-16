package service;

import controlador.LocalController;
import dao.LocalDAO;
import dao.LocalDAOImpl;
import model.Local;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalServiceImpl implements LocalService {
LocalDAO dao = new LocalDAOImpl();

LocalController controlador;
@Override
    public List<Local> listadoLocales() throws IOException {
        return dao.LecturaCSV();
    }

    @Override
    public void actualizarLocal(Local l) throws IOException {
    List<Local> locales = dao.getListaLocales();
    boolean encontrado = false;

    for(int i = 0;i<locales.size();i++){
        if(locales.get(i).getId().equalsIgnoreCase(l.getId())){
            locales.set(i, l);
            encontrado = true;
            break;
        }
    }
    if(!encontrado){
        locales.add(l);
    }
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(dao.getPath(),true))) {
        bw.newLine();
        bw.write(l.toString() );
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}


}
