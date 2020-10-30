package com.srcarrillo.ministerio.ui.radio;

import androidx.lifecycle.ViewModelProviders;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.srcarrillo.ministerio.R;

import java.io.IOException;

public class RadioFragment extends Fragment {

    private RadioViewModel mViewModel;
    private MediaPlayer mediaPlayer ;
    private ImageView imagePlayPause;
    boolean conectado;
    ProgressBar progressBar;


    public static RadioFragment newInstance() {
        return new RadioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.radio_fragment, container, false);
        imagePlayPause = view.findViewById(R.id.imagePlayPause);
        mediaPlayer =new MediaPlayer();

        imagePlayPause.setEnabled(false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        conectado = conectar();




        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imagePlayPause.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    imagePlayPause.setImageResource(R.drawable.ic_pause);
                }
            }
        });
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RadioViewModel.class);
        // TODO: Use the ViewModel
    }


    public boolean conectar(){
        Toast.makeText(getActivity().getApplicationContext(),"Intentado conectar",Toast.LENGTH_LONG).show();


        String url = "http://stm3.miradio.com.es:12036/"; // your URL here
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();// might take long! (for buffering, etc)
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Toast.makeText(getActivity().getApplicationContext(),"Conectado",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    imagePlayPause.setEnabled(true);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
    

