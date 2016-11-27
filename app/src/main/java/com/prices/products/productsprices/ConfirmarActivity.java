package com.prices.products.productsprices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.internal.Util;

import static com.prices.products.productsprices.R.id.textViewNome;
import static com.prices.products.productsprices.R.id.vereador;

public class ConfirmarActivity extends AppCompatActivity {

    private Candidato votoPrefeito;
    private Candidato votoVereador;
    private String token;

    private TextView nomePrefeito;
    private TextView partidoPrefeito;
    private ImageView imagemPrefeito;

    private TextView nomeVereador;
    private TextView partidoVereador;
    private ImageView imagemVereador;

    private Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        nomePrefeito = (TextView) findViewById(R.id.nomePrefeito);
        partidoPrefeito = (TextView) findViewById(R.id.partidoPrefeito);
        imagemPrefeito = (ImageView) findViewById(R.id.imagemPrefeito);

        nomeVereador = (TextView) findViewById(R.id.nomeVereador);
        partidoVereador = (TextView) findViewById(R.id.partidoVereador);
        imagemVereador = (ImageView) findViewById(R.id.imagemVereador);

        confirmar = (Button) findViewById(R.id.confirmar);

        if (getIntent() != null) {
            if (getIntent().getStringExtra("votoPrefeito") != null) {
                votoPrefeito = (Candidato) getIntent().getSerializableExtra("votoPrefeito");
                nomePrefeito.setText(votoPrefeito.getNome());
                partidoPrefeito.setText(votoPrefeito.getPartido());
                imagemPrefeito.setImageBitmap(votoPrefeito.getFoto());
            }
            if (getIntent().getStringExtra("votoVereador") != null) {
                votoVereador = (Candidato) getIntent().getSerializableExtra("votoVereador");
                nomeVereador.setText(votoVereador.getNome());
                partidoVereador.setText(votoVereador.getPartido());
                imagemVereador.setImageBitmap(votoVereador.getFoto());
            }
            if (getIntent().getStringExtra("token") != null) {
                token = getIntent().getStringExtra("token");
            }
        }

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.votar(token, votoVereador, votoPrefeito);
                Toast.makeText(getApplicationContext(), "Voto computado com sucesso", Toast.LENGTH_LONG);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vereador:
                openActivity(VereadorActivity.class);
                return true;
            case R.id.prefeito:
                openActivity(PrefeitoActivity.class);
                return true;
            case R.id.dashboard:
                openActivity(DashboardActivity.class);
                return true;
            case R.id.confirmar:
                openActivity(ConfirmarActivity.class);
                return true;
            case R.id.sair:
                openActivity(LoginActivity.class);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openActivity(Class activity) {
        Intent i = new Intent(this, activity);
        startActivity(i);
    }

}
