import model.Local;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class Locales {
    @Test
    public void pruebaLocales(){
        Local l = new Local("Id01","PEPITO", "PEPITO","15002","A Coruña","A Coruña", "981111111",1);
        Local l2 = new Local("Id02","PEPITO", "PEPITO","15002","A Coruña","A Coruña", "981111111",1);

        assertFalse(l.getId().equals(l2.getId()));
    }
}
