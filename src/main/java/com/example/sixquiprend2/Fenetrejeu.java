package com.example.jeu6quiprend;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.Region;

import java.util.List;

public class FenetreJeu extends Region {

    private static final int LARGEUR_FENETRE = 800;
    private static final int HAUTEUR_FENETRE = 800;

    private Canvas canvas;
    private GraphicsContext gc;

    public FenetreJeu() {
        canvas = new Canvas(LARGEUR_FENETRE, HAUTEUR_FENETRE);
        gc = canvas.getGraphicsContext2D();

        getChildren().add(canvas);
    }


    // Méthode pour dessiner le contenu du jeu
    public void dessinerJeu() {
        gc.clearRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        // Dessiner le plateau, les cartes, les scores, etc. en utilisant gc
        // Exemple : dessiner le plateau avec un fond de couleur
        gc.setFill(Color.SANDYBROWN);
        gc.fillRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        // Dessiner les cartes, les joueurs, les scores, etc.
    }

    public void dessinerPlateau() {
        // Définir les paramètres du plateau
        int largeurPlateau = 500;
        int hauteurPlateau = 500;
        int posXPlateau = (LARGEUR_FENETRE - largeurPlateau) / 2;
        int posYPlateau = (HAUTEUR_FENETRE - hauteurPlateau) / 2;

        // Dessiner le plateau avec un fond de couleur
        gc.setFill(Color.BROWN);
        gc.fillRect(posXPlateau, posYPlateau, largeurPlateau, hauteurPlateau);

        // Autres éléments du plateau (lignes, textes, etc.)
        // ...
    }

    public void dessinerCartes(List<Carte> cartes) {
        gc.clearRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        // Dessiner le plateau, les cartes, les scores, etc. en utilisant gc
        // Exemple : dessiner le plateau avec un fond de couleur
        gc.setFill(Color.SANDYBROWN);
        gc.fillRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        // Définir les dimensions des cartes
        double largeurCarte = 80;
        double hauteurCarte = 120;

        // Définir les marges et espacements entre les cartes
        double margeGauche = 20;
        double margeHaut = 20;
        double espacementX = 10;
        double espacementY = 10;

        // Calculer le nombre de colonnes et de lignes en fonction de la taille du plateau
        int nbColonnes = (int) (LARGEUR_FENETRE - 2 * margeGauche) / (int) (largeurCarte + espacementX);
        int nbLignes = (int) (HAUTEUR_FENETRE - 2 * margeHaut) / (int) (hauteurCarte + espacementY);

        // Dessiner les cartes sur le plateau
        int indexCarte = 0;
        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int colonne = 0; colonne < nbColonnes; colonne++) {
                if (indexCarte < cartes.size()) {
                    Carte carte = cartes.get(indexCarte);

                    // Calculer les positions de la carte
                    double posXCarte = margeGauche + colonne * (largeurCarte + espacementX);
                    double posYCarte = margeHaut + ligne * (hauteurCarte + espacementY);

                    // Dessiner la carte
                    if (carte.isFaceVisible()) {
                        gc.setFill(Color.WHITE);
                    } else {
                        gc.setFill(Color.LIGHTGRAY);
                    }
                    gc.fillRect(posXCarte, posYCarte, largeurCarte, hauteurCarte);

                    // Dessiner le contenu de la carte (ex. le numéro)
                    gc.setFill(Color.BLACK);
                    gc.fillText(String.valueOf(carte.getNumero()), posXCarte + 10, posYCarte + 20);

                    indexCarte++;
                }
            }
        }
    }

    // Autres méthodes et logique du jeu...

}



