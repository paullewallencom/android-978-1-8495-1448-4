package com.packtpub.packttunes;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;

public class ShopActivity extends Activity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Resources resources = getResources();

        setAlbum(new Album(
                resources.getDrawable(R.drawable.album_art),
                "The Android Quartet",
                new Artist(resources.getDrawable(R.drawable.sherlock),
                "Sherlock Peterson"),
                "Green Records",
                new Track("I was a robot", 208),
                new Track("Long is not enough time", 243),
                new Track("The rocket robot reel", 143),
                new Track("I love by bits", 188)));
    }

    private void setAlbum(final Album album) {
        final ViewGroup tracks = (ViewGroup)findViewById(R.id.track_listing);

        for(final Track t : album.getTracks()) {
            addTrackView(tracks, t);
        }

        final ImageView albumArt = (ImageView)findViewById(R.id.artwork);
        albumArt.setImageDrawable(album.getCover());

        final ImageView artistLogo = (ImageView)findViewById(R.id.artist_logo);
        artistLogo.setImageDrawable(album.getArtist().getLogo());

        final TextView albumLabel = (TextView)findViewById(R.id.album_label);
        albumLabel.setText(album.getName());

        final TextView recordLabel = (TextView)findViewById(R.id.record_label);
        recordLabel.setText(album.getLabel());

        final AmountBox amount = (AmountBox)findViewById(R.id.purchase_amount);
        amount.setFormat(new DecimalFormat("$ 0.##"));
        amount.setAmount(1.99 * album.getTracks().length);
    }

    private void addTrackView(final ViewGroup tracks, final Track track) {
        final LayoutInflater inflater = getLayoutInflater();
        final ViewGroup line = (ViewGroup)inflater.inflate(
                R.layout.track,
                tracks,
                false);

        final TextView trackName = (TextView)line.findViewById(R.id.track_name);
        trackName.setText(track.getName());

        final TextView trackTime = (TextView)line.findViewById(R.id.track_time);
        final StringBuilder builder = new StringBuilder();

        builder.append(track.getMinutes());
        builder.append(':');

        if(track.getSeconds() < 10) {
            builder.append('0');
        }

        builder.append(track.getSeconds());

        trackTime.setText(builder.toString());
        tracks.addView(line);
    }

}
