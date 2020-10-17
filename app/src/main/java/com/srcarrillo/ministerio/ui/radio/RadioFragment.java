package com.srcarrillo.ministerio.ui.radio;

import androidx.lifecycle.ViewModelProviders;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.srcarrillo.ministerio.R;

public class RadioFragment extends Fragment {

    private RadioViewModel mViewModel;
    private MediaPlayer mediaPlayer ;
    private ImageView imagePlayPause;
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    private Handler handler = new Handler();

    public static RadioFragment newInstance() {
        return new RadioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.radio_fragment, container, false);
        imagePlayPause = view.findViewById(R.id.imagePlayPause);
        textCurrentTime = view.findViewById(R.id.textCurrentTime);
        textTotalDuration = view.findViewById(R.id.textTotalDuration);
        playerSeekBar = view.findViewById(R.id.playerSeekBar);
        mediaPlayer =new MediaPlayer();



        playerSeekBar.setMax(100);

        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imagePlayPause.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    imagePlayPause.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });
        prepareMediaPlayer();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RadioViewModel.class);
        // TODO: Use the ViewModel
    }
    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource("https://ssl.xcast.com.br:12036/;");
            mediaPlayer.prepareAsync();
            textTotalDuration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));

        } catch (Exception exception){
            Toast.makeText(getActivity(), exception.getMessage() ,Toast.LENGTH_LONG).show();

        }
    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };
    private void updateSeekBar(){

        if (mediaPlayer.isPlaying()){
            playerSeekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition()/ mediaPlayer.getDuration())* 100));
            handler.postDelayed(updater, 1000);
        }
    }

   private String milliSecondsToTimer(long milliSeconds){
        String timerString = "";
        String secondsString;

        int hours = (int)(milliSeconds / (1000*60*60));
        int minutes = (int)(milliSeconds % (1000*60*60) ) / (1000*60);
        int seconds = (int)((milliSeconds % (1000*60*60)) % (1000*60) / 1000);

        if (hours > 0 ){
            timerString = hours + " : ";
        }
        if (seconds < 10){
            secondsString = "0" + seconds;
        }else {
            secondsString = "" + seconds;
        }
        timerString = timerString + minutes + " : " +  secondsString;
        return timerString;
    }

}