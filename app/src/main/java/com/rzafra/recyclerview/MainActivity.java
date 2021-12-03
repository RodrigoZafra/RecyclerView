package com.rzafra.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MiAdaptadorSimple adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Caballo");
        animalNames.add("Vaca");
        animalNames.add("Perro");
        animalNames.add("Gato");
        animalNames.add("Leon");
        animalNames.add("Tigre");
        animalNames.add("Rinoceronte");
        animalNames.add("Elefante");
        animalNames.add("Aguila");
        animalNames.add("Mariposa");
        animalNames.add("Serpiente");
        animalNames.add("Oso");

        RecyclerView recyclerView = findViewById(R.id.rvAnimales);
        LinearLayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        adapter = new MiAdaptadorSimple(this, animalNames);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        TextView tvAnimalAñadido = findViewById(R.id.tvAnimal);
        Button bAñadir = findViewById(R.id.añadir);
        bAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = tvAnimalAñadido.getText().toString();
                int posicionInsercion = (adapter.getPos() >= 0) ? adapter.getPos() + 1 : 0;
                animalNames.add(posicionInsercion, item);
                adapter.notifyItemInserted(posicionInsercion);
                adapter.notifyItemRangeChanged(0, animalNames.size());
            }
        });
        Button bBorrar = findViewById(R.id.borrar);
        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = adapter.getPos();
                if (adapter.getPos() >= 0) {
                    animalNames.remove(adapter.getPos());
                    adapter.notifyItemRemoved(adapter.getPos());
                    adapter.notifyItemRangeChanged(0, animalNames.size());
                    adapter.decrementarPos();
                }
            }
        });
    }
}