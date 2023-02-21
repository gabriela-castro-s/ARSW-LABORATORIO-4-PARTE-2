package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filtroRedundancia;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import org.junit.Assert;
import org.junit.Test;

public class FilterServiceTest {

    @Test
    public void DeberiaFiltrarPorRedundanciaBlueprint() throws BlueprintNotFoundException, BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        filtroRedundancia fr = new filtroRedundancia();
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15,15),new Point(40,40), new Point(40,40),new Point(15, 15),new Point(15,15)};
        Point[] pts1 = new Point[]{new Point(15,14)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        Blueprint bp1 = new Blueprint("prueba","2",pts1);
        Blueprint bp2 = new Blueprint("prueba","3",new Point[]{});
        ibpp.saveBlueprint(bp0);
        fr.filtro(bp1);
        Assert.assertEquals(1,bp1.getPoints().size());
        ibpp.saveBlueprint(bp2);
        fr.filtro(bp2);
        Assert.assertEquals(0,bp2.getPoints().size());
    }
}
