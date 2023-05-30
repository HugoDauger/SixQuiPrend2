package com.example.sixquiprend;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jeu 6 Qui Prend");

        // Création de la fenêtre de jeu
        FenetreJeu fenetreJeu = new FenetreJeu();

        // Création de la scène principale
        Scene scene = new Scene(fenetreJeu, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Création et initialisation de la liste de cartes
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(1));
        cartes.add(new Carte(2));

        // Dessiner le contenu du jeu
        fenetreJeu.dessinerJeu();
        fenetreJeu.dessinerPlateau();
        fenetreJeu.dessinerCartes(cartes);
    }

    public static void main(String[] args) {
        launch();
    }
}
