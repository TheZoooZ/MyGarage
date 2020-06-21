package android.pk.mygarage.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.Database.DbContext;
import android.pk.mygarage.R;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AssetDetails extends AppCompatActivity {

    DbContext dbContext;
    Asset asset;

    Context context;
    ImageButton backToMainActivityButton;
    ImageButton deleteAssetButton;

    TextView assetNameTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView purchaseDateTextView;
    TextView rowTextView;
    TextView columnTextView;
    TextView warranty;

    public AssetDetails() {
        this.dbContext = new DbContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_details);

        context = this;
        backToMainActivityButton = findViewById(R.id.backToMainActivityButton);
        deleteAssetButton = findViewById(R.id.deleteAssetButton);

        assetNameTextView = findViewById(R.id.assetNameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        purchaseDateTextView = findViewById(R.id.dateTextView);
        rowTextView = findViewById(R.id.rowTextView);
        columnTextView = findViewById(R.id.columnTextView);
        warranty = findViewById(R.id.warrantyTextView);

        Intent currentIntent = getIntent();

        asset = (Asset) currentIntent.getSerializableExtra("asset");

        assetNameTextView.setText(asset.name);
        descriptionTextView.setText(asset.description);
        categoryTextView.setText(asset.type.assetType);
        purchaseDateTextView.setText(convertToDateStringFormat(asset.purchaseDate));
        rowTextView.setText(String.valueOf(asset.row));
        columnTextView.setText(String.valueOf(asset.column));
        warranty.setText(String.valueOf(asset.yearsOfWarranty));

        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(context, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        deleteAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isRemoved = dbContext.tryRemoveAsset(asset.id);

                if(isRemoved){
                    Toast.makeText(context, "Asset " + asset.name +" removed.", Toast.LENGTH_LONG).show();
                    Intent mainActivityIntent = new Intent(context, MainActivity.class);
                    startActivity(mainActivityIntent);
                }
                else{
                    Toast.makeText(context, "Cannot remove this asset.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String convertToDateStringFormat(Date purchaseDate) {
        return purchaseDate.getDay() + " - " + purchaseDate.getMonth() + " - " + purchaseDate.getYear();
    }
}
