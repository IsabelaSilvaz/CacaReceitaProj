package com.example.cacareceitaproj.classes;

public class Card {
    private String titulo;
    private int imagemCard;

    public Card(String titulo, int imagemCard) {
        this.titulo = titulo;
        this.imagemCard = imagemCard;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getImagemCard() {
        return imagemCard;
    }
}
