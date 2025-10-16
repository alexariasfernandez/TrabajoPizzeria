package service;

import dao.LandingPageContentDAO;
import model.LandingPageContent;
import vista.IdiomaView;
import vista.LandingPageView;

import javax.swing.*;

public class LandingPageServiceImpl implements LandingPageService {
    LandingPageContentDAO dao;
    public LandingPageServiceImpl(LandingPageContentDAO dao) {
        this.dao = dao;
    }

    @Override
    public LandingPageContent getLandingPageContent(String idioma) {

        return dao.RecuperarInformacion(idioma);
    }
}
