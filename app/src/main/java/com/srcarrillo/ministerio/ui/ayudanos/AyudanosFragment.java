package com.srcarrillo.ministerio.ui.ayudanos;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.srcarrillo.ministerio.R;

public class AyudanosFragment extends Fragment {

    private AyudanosViewModel mViewModel;



    public static AyudanosFragment newInstance() {
        return new AyudanosFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ayudanos_fragment, container, false);
        Button btnEnviar = view.findViewById(R.id.btnEnviar);
        final EditText txtNombre = view.findViewById(R.id.txtNombre);
        final EditText txtComentario = view.findViewById(R.id.txtComentario);
        final RatingBar ratingBar = view.findViewById(R.id.ratingBar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail(txtNombre.getText().toString()+":\n"+txtComentario.getText().toString()+"\n"+ratingBar.getRating()+" Estrellas");
            }
        });
        return view;

        //return inflater.inflate(R.layout.ayudanos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AyudanosViewModel.class);

        // TODO: Use the ViewModel
    }
    public void enviarEmail(String mensaje){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);


        String mailto = "mailto:ThomasCarrilloGonzalez@gmail.com" +
                "?cc=" + " " +
                "&body=" + Uri.encode(mensaje);

        emailIntent.setData(Uri.parse(mailto));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "thomascarrillogonzalez@gmail.com"); // * configurar email aquí!
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Ministerio Puerta Abierta - Opinión");
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email."));
            Log.i("EMAIL", "Muchas Gracias por ayudarnos, Bendiciones");
        }
        catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(getActivity(), "NO existe ningún cliente de email instalado!", Toast.LENGTH_LONG).show();
        }

    }

}