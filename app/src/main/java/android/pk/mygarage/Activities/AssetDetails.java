package android.pk.mygarage.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.R;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AssetDetails extends AppCompatActivity {

    Context context;
    ImageButton backToMainActivityButton;
    TextView assetNameTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView purchaseDateTextView;
    TextView rowTextView;
    TextView columnTextView;
    TextView warranty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_details);

        context = this;
        backToMainActivityButton = findViewById(R.id.backToMainActivityButton);

        assetNameTextView = findViewById(R.id.assetNameTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        purchaseDateTextView = findViewById(R.id.dateTextView);
        rowTextView = findViewById(R.id.rowTextView);
        columnTextView = findViewById(R.id.columnTextView);
        warranty = findViewById(R.id.warrantyTextView);

        Intent currentIntent = getIntent();

        Asset asset = (Asset) currentIntent.getSerializableExtra("asset");

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
    }

    private String convertToDateStringFormat(Date purchaseDate) {
        return purchaseDate.getDay() + " - " + purchaseDate.getMonth() + " - " + purchaseDate.getYear();
    }
}
