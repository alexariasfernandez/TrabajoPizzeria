package service;

import dao.LandingPageContentDAOJAXB;
import model.LandingPageContent;

public class LandingPageServiceMock implements LandingPageService {
LandingPageContentDAOJAXB dao;
    @Override
    public LandingPageContent getLandingPageContent(String idioma) {
        dao = new LandingPageContentDAOJAXB();
        return new LandingPageContent(dao.getQuienesomos(),
                dao.getNuestrapasion(),
                dao.getExperiencia());
    }
}
