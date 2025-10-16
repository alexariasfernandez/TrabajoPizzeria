package dao;

import model.Local;

import java.util.List;

public interface LocalDAO {
    public List<Local> LecturaCSV();
    public String getPath();
    public List<Local> getListaLocales();
}