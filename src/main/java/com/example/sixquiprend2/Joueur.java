package com.example.sixquiprend2;
package com.example.jeu6quiprend;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Joueur {
    private String nom;
    private int score;
    private List<Carte> cartesEnMain;

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

    public void distribuerCartes(List<Carte> cartes) {
        int nbCartesDistribuees = Math.min(cartes.size(), 10); // Le nombre de cartes à distribuer (maximum de 10)

        for (int i = 0; i < nbCartesDistribuees; i++) {
            Carte carte = cartes.remove(0); // Retire la première carte du paquet
            ajouterCarteEnMain(carte);
        }
    }
}

