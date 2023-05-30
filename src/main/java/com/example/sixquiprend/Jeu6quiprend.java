package com.example.sixquiprend;

import java.util.*;

public class Jeu6QuiPrend {
    private List<Joueur> joueurs;
    private List<Carte> cartes;
    private List<List<Carte>> series;
    private List<List<Carte>> rangees;
    private int tourActuel;

    public Jeu6QuiPrend(List<String> nomsJoueurs) {
        // Création des joueurs
        joueurs = new ArrayList<>();
        for (String nomJoueur : nomsJoueurs) {
            joueurs.add(new Joueur(nomJoueur));
        }

        // Initialisation des rangées
        rangees = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            rangees.add(new ArrayList<>());
            rangees.get(i).add(cartes.remove(0)); // Ajoutez une carte aléatoire à la rangée
        }

        // Création des cartes
        cartes = new ArrayList<>();
        for (int i = 1; i <= 104; i++) {
            cartes.add(new Carte(i));
        }

        // Mélange des cartes
        Collections.shuffle(cartes);

        // Initialisation des séries
        series = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            series.add(new ArrayList<>());
        }

        // Distribution des cartes aux joueurs
        for (Joueur joueur : joueurs) {
            for (int i = 0; i < 10; i++) {
                Carte carte = cartes.remove(0);
                joueur.ajouterCarteEnMain(carte);
            }
        }
        tourActuel = 0;
    }


    private void miseEnJeuDesCartes() {

        // Ajout des cartes déposées dans les rangées
        for (int i = 0; i < joueurs.size(); i++) {
            Joueur joueur = joueurs.get(i);
            Carte carte = joueur.getCarteDeposee();
            carte.setFaceVisible(true);
            rangees.get(i).add(carte); // Ajoutez la carte à la rangée correspondante
        }
        // Tri des joueurs par ordre croissant de la valeur de leur carte déposée
        joueurs.sort(Comparator.comparingInt(j -> j.getCarteDeposee().getNumero()));

        for (Joueur joueur : joueurs) {
            Carte carte = joueur.getCarteDeposee();

            // Recherche de la série où la carte peut être déposée
            int differenceMinSerie = Integer.MAX_VALUE;
            int serieMin = -1;
            for (int i = 0; i < series.size(); i++) {
                List<Carte> serie = series.get(i);
                if (serie.isEmpty() || carte.getNumero() - serie.get(serie.size() - 1).getNumero() < differenceMinSerie) {
                    differenceMinSerie = carte.getNumero() - serie.get(serie.size() - 1).getNumero();
                    serieMin = i;
                }
            }

            // Vérifier si la série est terminée
            if (series.get(serieMin).size() == 5) {
                // Encaissement des cartes de la série terminée
                List<Carte> serieTerminee = series.get(serieMin);
                joueur.encaisserCartes(serieTerminee);
                serieTerminee.clear(); // Vider la série terminée
                series.set(serieMin, new ArrayList<>()); // Ajouter une nouvelle série vide
            }

            // Déposer la carte dans la série appropriée
            if (serieMin != -1) {
                series.get(serieMin).add(carte);
            } else {
                // Règle n°4: La carte la plus faible
                // Le joueur remplace une rangée existante avec sa carte
                int rangeeARemplacer = joueur.choisirRangeeARemplacer(rangees);
                if (rangeeARemplacer != -1) {
                    List<Carte> rangée = rangees.get(rangeeARemplacer);
                    joueur.encaisserCartes(rangée); // Encaisser les cartes de la rangée
                    rangée.clear(); // Vider la rangée
                    rangée.add(carte); // Ajouter la carte du joueur dans la rangée
                } else {
                    // Ajouter la carte à la pile de têtes de boeuf du joueur
                    joueur.ajouterCartePileTetesDeBoeuf(carte);
                }
            }
        }
        // Vérifier si la manche est terminée
        tourActuel++;
        if (tourActuel == 10) {
            // Calcul des points négatifs pour chaque joueur
            for (Joueur joueur : joueurs) {
                int pointsNegatifs = joueur.getPileTetesDeBoeuf().size();
                joueur.augmenterScore(pointsNegatifs);
            }

            // Recherche du joueur avec le moins de têtes de bœuf
            Joueur joueurGagnant = joueurs.get(0);
            int teteDeBoeufMin = joueurGagnant.getPileTetesDeBoeuf().size();
            for (int i = 1; i < joueurs.size(); i++) {
                Joueur joueur = joueurs.get(i);
                int teteDeBoeuf = joueur.getPileTetesDeBoeuf().size();
                if (teteDeBoeuf < teteDeBoeufMin) {
                    joueurGagnant = joueur;
                    teteDeBoeufMin = teteDeBoeuf;
                }
            }

            // Afficher le joueur gagnant avec le moins de têtes de bœuf
            System.out.println("Le joueur gagnant est : " + joueurGagnant.getNom() + " avec " + teteDeBoeufMin + " tête(s) de bœuf.");
        }
    }

}
