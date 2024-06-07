package com.example.cacareceitaproj.utils;

import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("instructions")
    private String instructions;

    // Getters e Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}