package com.example.cacareceitaproj.classes;

public class Card {
    private String titulo;
    private String imagemCard;

    public Card(String titulo, String imagemCard) {
        this.titulo = titulo;
        this.imagemCard = imagemCard;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImagemCard() {
        return imagemCard;
    }
}
