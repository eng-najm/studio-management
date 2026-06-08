package com.studio.core.model;

public class ComboItem<T> {
    private final T value;
    private final String display;

    public ComboItem(T value, String display) {
        this.value = value;
        this.display = display;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return display;
    }
}
