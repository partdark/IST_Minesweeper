package com.example.istminesweeper;

public class Cell {
    public static final int BOMB = -1;
    public static final int BLANK = 0;

    public int value;
    private boolean isRevealed;
    private boolean isFlagged;

    public Cell(int value) {
        this.value = value;
        this.isRevealed = false;
        this.isFlagged = false;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}