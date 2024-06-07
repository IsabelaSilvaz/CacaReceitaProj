package com.example.cacareceitaproj.classes;

import android.view.View;

public class Card {
    private String titulo;
    private String imagemCard;
    private View.OnClickListener onClickListener;

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

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}

