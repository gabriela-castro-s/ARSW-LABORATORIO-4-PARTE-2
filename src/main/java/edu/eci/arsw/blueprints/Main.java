package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.*;
import edu.eci.arsw.blueprints.persistence.*;
import edu.eci.arsw.blueprints.services.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException{

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpService = ac.getBean(BlueprintsServices.class);

        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10), new Point(10,10)};
        Blueprint bp=new Blueprint("Gabriela", "Nuevo Blueprint",pts);

        Point[] pts2=new Point[]{new Point(214, 214),new Point(215, 123),new Point(214, 214), new Point(22,25)};
        Blueprint bp2=new Blueprint("Carol", "Blueprint 2",pts2);

        Blueprint bp3=new Blueprint("Carol","Blueprint 3");
        Blueprint bp4=new Blueprint("Gabriela","Blueprint 4");


        // Agregar planos
        try {
            bpService.addNewBlueprint(bp);
            bpService.addNewBlueprint(bp2);
            bpService.addNewBlueprint(bp3);
            bpService.addNewBlueprint(bp4);
        } catch (BlueprintPersistenceException ex) {
            System.out.println("Error:" + ex.getMessage());

        }

        // Consultar todos los planos
        Set<Blueprint> bPrint = new HashSet<>();

        try {
            bPrint = bpService.getAllBlueprints();
            System.out.println(bPrint.toString());
        } catch (BlueprintNotFoundException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

        // Consultar todos los planos de un autor
        try {
            bPrint=bpService.getBlueprintsByAuthor("Carol");
            System.out.println(bPrint.toString());
        } catch (BlueprintNotFoundException ex) {
            System.out.println("Error:" + ex.getMessage());
        }


        // Consultar plano dado el nombre y el autor
        try {
            System.out.println("Consultar plano: "+bpService.getBlueprint("Gabriela", "Blueprint 4").toString());
        } catch (BlueprintNotFoundException ex) {
            System.out.println("Error:" + ex.getMessage());
        }




    }
}
