package com.packtpub.packttunes;

import android.graphics.drawable.Drawable;

public class Album {

    private final Drawable cover;

    private final String name;

    private final Artist artist;

    private final String label;

    private final Track[] tracks;

    public Album(
            final Drawable cover,
            final String name,
            final Artist artist,
            final String label,
            final Track... tracks) {

        this.cover = cover;
        this.name = name;
        this.artist = artist;
        this.label = label;
        this.tracks = tracks;
    }

    public Drawable getCover() {
        return cover;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public Track[] getTracks() {
        return tracks;
    }

}
