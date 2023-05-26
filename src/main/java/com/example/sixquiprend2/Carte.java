package com.example.sixquiprend2;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Carte {
  private final int numero;
  private final int tetesDeboeuf;

    public Carte(int numero) {
        this.numero = numero;
        this.tetesDeboeuf = calculerTetesDeBoeuf(numero);
    }
  
  private int calculerTetesDeBoeuf(int numero) {
        if (numero == 55) {
            return 7; // "55" est à la fois un doublet et un nombre en 5
        } else if (numero % 11 == 0) {
            return 5; // Doublet
        } else if (numero % 10 == 0) {
            return 3; // Nombre en 0
        } else if (numero % 5 == 0) {
            return 2; // Nombre en 5
        } else {
            return 1; // Aucun critère spécial, 1 tête de bœuf
        }
  }
}
