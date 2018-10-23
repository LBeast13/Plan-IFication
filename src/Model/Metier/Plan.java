package Model.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class Plan{

    private final Map<String, List<Troncon>> plan;

    public Plan(Map<String, List<Troncon>> plan) {
        this.plan = plan;
    }

    public List<Troncon> getSuccesseurs(String idNoeud) {
        return plan.get(idNoeud);
    }

    public List<String> getNoeuds() {
        return new ArrayList<>(plan.keySet());
    }

    public double getDistance(String idSource, String idDestination){
        List<Troncon> troncons = plan.get(idSource);
        for (Troncon troncon: troncons){
            if (troncon.getDestination().equals(idDestination)) return troncon.longueur;
        }

        return Double.POSITIVE_INFINITY;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "plan=" + plan +
                '}';
    }

    public static class Troncon {
        private final String idOrigine;
        private final String idDestination;
        private final double longueur;
        private final String nomDeLaRue;

        public Troncon(String idOrigine, String idDestination, double longueur, String nomDeLaRue) {
            this.idOrigine = idOrigine;
            this.idDestination = idDestination;
            this.longueur = longueur;
            this.nomDeLaRue = nomDeLaRue;
        }

        public double getLongueur() {
            return longueur;
        }

        public String getOrigine() {
            return idOrigine;
        }

        public String getDestination() {
            return idDestination;
        }

        @Override
        public String toString() {
            return "Troncon{" +
                    "idOrigine='" + idOrigine + '\'' +
                    ", idDestination='" + idDestination + '\'' +
                    ", longueur=" + longueur +
                    ", nomDeLaRue='" + nomDeLaRue + '\'' +
                    '}';
        }
    }
}
