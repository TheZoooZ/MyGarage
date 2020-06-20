package android.pk.mygarage.Activities;

import android.os.Bundle;
import android.pk.mygarage.ListViewAdapter;
import android.pk.mygarage.R;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String[] AssetNames = {
            "Wiertarka",
            "Sruba",
            "Nóż",
            "Korkociąg",
            "Maczeta",
            "Gogle",
            "Piłka do metalu",
            "Wygłuszacze",
            "Wasserwaga",
            "Metr"
    };
    private final String[] AssetDescriptions = {
            "Dobra do wiercenia",
            "Srednica 22mm",
            "Badzo ostry, lepiej uważać",
            "Wczoraj otwierałem nim szampana",
            "Kupiłem u kolegów",
            "Fajnie się komponuje z maczetą",
            "Narzędzie do zadań specjalnych!",
            "Moim uszom nic nie szkodzi!",
            "Trzyma swój poziom...",
            "Mierzę nim siły na zamiary"
    };

    ListView listView;
    ImageButton searchButton;
    ImageButton addAssetButton;
    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        searchButton = findViewById(R.id.searchButton);
        addAssetButton = findViewById(R.id.addAssetButton);
        textInput = findViewById(R.id.searchInput);

        ListViewAdapter adapter = new ListViewAdapter(this, AssetNames, AssetDescriptions);
        listView.setAdapter(adapter);
    }
}
