package service;

import dao.LocalDAO;
import dao.LocalDAOImpl;
import model.Local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalServiceImpl implements LocalService {
LocalDAO dao = new LocalDAOImpl();
    @Override
    public List<Local> listadoLocales() throws IOException {
        return dao.LecturaCSV();
    }

    @Override
    public void actualizarLocal(Local l) throws IOException {

    }
}
