package com.vogella.jersey.first.client;

public class TestObj {
    private int i;

    public TestObj(String str) {
        this.i = Integer.parseInt(str) + 10;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}