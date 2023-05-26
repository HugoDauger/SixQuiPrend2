package com.example.jeu6quiprend;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Joueur {
    private String nom;
    private int score;
    private List<Carte> cartesEnMain;
    private Carte carteDeposee;
    private List<Carte> pileTetesDeBoeuf;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.cartesEnMain = new ArrayList<>();
    }

    public void ajouterCarteEnMain(Carte carte) {

        cartesEnMain.add(carte);
    }

    public void retirerCarteEnMain(Carte carte) {

        cartesEnMain.remove(carte);
    }

    public List<Carte> getCartesEnMain() {

        return cartesEnMain;
    }

    public void augmenterScore(int points) {
        score += points;
    }

    public void ajouterCartePileTetesDeBoeuf(Carte carte) {
        pileTetesDeBoeuf.add(carte);
    }


    public void distribuerCartes(List<Carte> cartes) {
        int nbCartesDistribuees = Math.min(cartes.size(), 10); // Le nombre de cartes à distribuer (maximum de 10)

        for (int i = 0; i < nbCartesDistribuees; i++) {
            Carte carte = cartes.remove(0); // Retire la première carte du paquet
            ajouterCarteEnMain(carte);
        }
    }

    public Carte getCarteAleatoireEnMain() {
        Random random = new Random();
        int index = random.nextInt(cartesEnMain.size());
        return cartesEnMain.get(index);
    }

    public void encaisserCartes(List<Carte> cartes) {
        cartesEnMain.addAll(cartes);
    }

    public int choisirRangeeARemplacer(List<List<Carte>> rangees) {
        // Afficher les rangées disponibles et permettre au joueur de choisir
        System.out.println("Rangées disponibles : ");
        for (int i = 0; i < rangees.size(); i++) {
            List<Carte> rangée = rangees.get(i);
            if (rangée.isEmpty()) {
                System.out.println("Rangée " + (i + 1) + " : Vide");
            } else {
                System.out.println("Rangée " + (i + 1) + " : " + rangée.get(rangée.size() - 1).getNumero());
            }
        }

        // Demander au joueur de choisir une rangée
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choisissez le numéro de la rangée que vous voulez remplacer : ");
        int choix = scanner.nextInt();

        // Vérifier si le choix est valide
        if (choix >= 1 && choix <= rangees.size()) {
            return choix - 1;
        } else {
            return -1;
        }
    }
}
