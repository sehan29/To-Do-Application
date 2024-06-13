package com.example.to_do_app;

public class Item {
    private String name;
    private String description;
    private boolean isSelected;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}