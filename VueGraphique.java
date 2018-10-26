package Vue;

import Model.Metier.*;
import Model.Planification;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.Dimension;
import java.util.*;


public class VueGraphique extends Vue {
    private double minLongitude = Double.POSITIVE_INFINITY, maxLatitude = Double.NEGATIVE_INFINITY;
    private double maxLongitude = Double.NEGATIVE_INFINITY, minLatitude = Double.POSITIVE_INFINITY;
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private double HEIGHT = dimension.getHeight();
    private double echele;
    
    private Group rootGroup = new Group();
    private List<Color> colors;

    public VueGraphique(Planification planification) {
        super(planification);
        this.getChildren().add(rootGroup);
        initCouleurs();
    }

    private void initCouleurs() {
        colors = new LinkedList<>();
        colors.add(Color.BROWN);
        colors.add(Color.SEAGREEN);
        colors.add(Color.AQUA);
        colors.add(Color.YELLOWGREEN);
        colors.add(Color.CRIMSON);
        colors.add(Color.BLUEVIOLET);
        colors.add(Color.BLUEVIOLET);
        colors.add(Color.SPRINGGREEN);
        colors.add(Color.TOMATO);
        colors.add(Color.YELLOW);
        colors.add(Color.CORNFLOWERBLUE);
    }


    @Override
    void dessinerPlan() {
        calculerCoins();

        Group linesGroupe = new Group();
        Group circlesGroupe = new Group();

        for (String idNoeud : plan.getNoeuds()) {
            for (Model.Metier.Plan.Trancon trancon : plan.getSuccesseurs(idNoeud)) {
                Model.Metier.Noeud n1 = Model.Metier.NoeudFactory.getNoeudParId(idNoeud);
                Cercle cercle = new Cercle(trX(n1.getLongitude()), trY(n1.getLatitude()),5,Color.TRANSPARENT,n1);
                circlesGroupe.getChildren().add(cercle);
                Model.Metier.Noeud n2 = Model.Metier.NoeudFactory.getNoeudParId(trancon.getDestination());
                Ligne line = new Ligne(trX(n1.getLongitude()), trY(n1.getLatitude()), trX(n2.getLongitude()), trY(n2.getLatitude()),trancon);
                linesGroupe.getChildren().add(line);
            }
        }
        rootGroup.getChildren().add(linesGroupe);
        rootGroup.getChildren().add(circlesGroupe);
    }

    @Override
    void dessinerDemandeDeLivraisons() {
        Group livraisonsGroup = new Group();

        Noeud entrepot = NoeudFactory.getNoeudParId(demandeLivraisons.getEntrepot());

        Circle circleEntrepot = new Circle(trX(entrepot.getLongitude()), trY(entrepot.getLatitude()), 8, Color.RED);
        livraisonsGroup.getChildren().add(circleEntrepot);

        for (Livraison livraison : demandeLivraisons.getPointsDeLivraisons()) {
            Noeud pointLivr = NoeudFactory.getNoeudParId(livraison.getNoeud());
            Circle circleLivr = new Circle(trX(pointLivr.getLongitude()), trY(pointLivr.getLatitude()), 8, Color.BLUE);
            livraisonsGroup.getChildren().add(circleLivr);
        }

        rootGroup.getChildren().add(livraisonsGroup);
    }

    @Override
    void dessinerTournees() {
        Group tourneesGroup = new Group();
        for (Tournee tournee : tournees) {
            Color color = getColor();
            List<Chemin> chemins = tournee.getChemins();
            for (Chemin chemin : chemins) {
                Noeud premierNoeud = NoeudFactory.getNoeudParId(chemin.getDepart());
                Line line = new Line();
                line.setStartX(trX(premierNoeud.getLongitude()));
                line.setStartY(trY(premierNoeud.getLatitude()));
                for (String idNoeud : chemin.getChemin()) {
                    Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
                    line.setEndX(trX(noeud.getLongitude()));
                    line.setEndY(trY(noeud.getLatitude()));

                    line.setStroke(color);
                    line.setStrokeWidth(3);
                    tourneesGroup.getChildren().add(line);
                    line = new Line();
                    line.setStartX(trX(noeud.getLongitude()));
                    line.setStartY(trY(noeud.getLatitude()));
                }
            }
        }

        rootGroup.getChildren().add(tourneesGroup);
    }

    private Color getColor() {
        if (colors.isEmpty()){
            Random random =new Random();
            return Color.color(random.nextInt(100)/100.0,random.nextInt(100)/100.0,random.nextInt(100)/100.0);
        }
        return colors.remove(0);
    }

    private double trX(double longitude) {
         echele = (HEIGHT / (maxLongitude - minLongitude))*((maxLatitude - minLatitude)/(maxLongitude-minLongitude));
        return echele * (longitude - minLongitude);
        
    }

    private double trY(double latitude) {
         echele = (HEIGHT / (maxLatitude - minLatitude))*((maxLatitude - minLatitude)/(maxLongitude-minLongitude));
        return echele * (maxLatitude - latitude);
    }

    private void calculerCoins() {
        for (String idNoeud : plan.getNoeuds()) {
            Noeud noeud = NoeudFactory.getNoeudParId(idNoeud);
            if (noeud.getLongitude() < minLongitude) minLongitude = noeud.getLongitude();
            if (noeud.getLongitude() > maxLongitude) maxLongitude = noeud.getLongitude();
            if (noeud.getLatitude() < minLatitude) minLatitude = noeud.getLatitude();
            if (noeud.getLongitude() > maxLatitude) maxLatitude = noeud.getLatitude();
            
        }
        System.out.println(maxLongitude-minLongitude);
        System.out.println(maxLatitude-minLatitude);
        
    }
}



