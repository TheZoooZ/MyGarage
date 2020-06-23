package android.pk.mygarage.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.pk.mygarage.CategoriesListAdapter;
import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.Core.Models.AssetType;
import android.pk.mygarage.Database.DbContext;
import android.pk.mygarage.ListViewAdapter;
import android.pk.mygarage.R;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView assetsListView;
    private ListView categoriesListView;
    private ImageButton addAssetButton;
    private ImageButton categoriesButton;
    private EditText searchInput;
    private Context context;

    private DbContext dbContext;

    public MainActivity() {
        dbContext = new DbContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriesButton = findViewById(R.id.categoriesButton);
        assetsListView = findViewById(R.id.assetsListView);
        categoriesListView = findViewById(R.id.categoriesListView);
        addAssetButton = findViewById(R.id.addAssetButton);
        searchInput = findViewById(R.id.searchInput);
        context = this;

        List<Asset> assets = dbContext.getAssets();
        List<AssetType> assetTypes = dbContext.getAssetTypes();

        ListViewAdapter listViewAdapter = new ListViewAdapter(context, assets);
        assetsListView.setAdapter(listViewAdapter);

        CategoriesListAdapter categoriesListAdapter = new CategoriesListAdapter(context, assetTypes);
        categoriesListView.setAdapter(categoriesListAdapter);

        addAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewAssetIntent = new Intent(context, AddAssetActivity.class);
                startActivity(addNewAssetIntent);
            }
        });

        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoriesListView.getVisibility() == View.INVISIBLE)
                    categoriesListView.setVisibility(View.VISIBLE);
                else
                    categoriesListView.setVisibility(View.INVISIBLE);
            }
        });

        assetsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Asset asset = (Asset) assetsListView.getItemAtPosition(position);

                Intent myIntent = new Intent(context, AssetDetails.class);
                myIntent.putExtra("asset", asset);
                startActivity(myIntent);
            }
        });

        categoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AssetType assetType = (AssetType) categoriesListView.getItemAtPosition(position);

                ListViewAdapter adapter = new ListViewAdapter(context, dbContext.getAssetsByType(assetType.assetType));
                assetsListView.setAdapter(adapter);

                categoriesListView.setVisibility(View.INVISIBLE);
            }
        });

        searchInput.addTextChangedListener(new TextWatcher() {
            ListViewAdapter adapter;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = String.valueOf(s);

                if(key.isEmpty()){
                    adapter = new ListViewAdapter(context, dbContext.getAssets());
                    assetsListView.setAdapter(adapter);
                }
                else{
                    adapter = new ListViewAdapter(context, dbContext.getAssetsByName(key));
                    assetsListView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
