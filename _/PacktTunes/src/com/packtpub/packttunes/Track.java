package com.packtpub.packttunes;

public class Track {

    private final String name;

    private final int length;

    public Track(
            final String name,
            final int length) {

        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public int getMinutes() {
        return length / 60;
    }

    public int getSeconds() {
        return length % 60;
    }

}
