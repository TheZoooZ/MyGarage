package android.pk.mygarage.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.pk.mygarage.CategoriesListAdapter;
import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.Core.Models.AssetType;
import android.pk.mygarage.Database.DbContext;
import android.pk.mygarage.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AddAssetActivity extends AppCompatActivity {

    AssetType assetType;
    Date purchaseDate;

    private Context context;
    private Intent mainActivityIntent;

    private DbContext dbContext;

    private TextView assetNameTextInput;
    private TextView descriptionTextInput;
    private TextView categoryTextInput;
    private TextView dateTextInput;
    private TextView rowTextInput;
    private TextView columnTextInput;
    private CalendarView calendar;
    private ListView categories;
    private TextView warrantyTextInput;

    public AddAssetActivity() {
        this.dbContext = new DbContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);

        context = this;
        mainActivityIntent = new Intent(context, MainActivity.class);

        ImageButton categoriesChoiceButton = findViewById(R.id.categoriesChoiceButton);
        ImageButton calendarDateChoiceButton = findViewById(R.id.dateChoiceButton);
        Button addAssetButton = findViewById(R.id.addAssetConfirmationButton);
        ImageButton backToMainActivityButton = findViewById(R.id.backToMainActivityButton);

        assetNameTextInput = findViewById(R.id.assetNameTextInput);
        descriptionTextInput = findViewById(R.id.descriptionTextInput);
        categoryTextInput = findViewById(R.id.categoryTextInput);
        dateTextInput = findViewById(R.id.dateTextInput);
        rowTextInput = findViewById(R.id.rowTextInput);
        columnTextInput = findViewById(R.id.columnTextInput);
        calendar = findViewById(R.id.calendarDateChoiceView);
        categories = findViewById(R.id.categoriesChoiceListView);
        warrantyTextInput = findViewById(R.id.warrantyTextInput);

        calendar.setVisibility(View.GONE);
        categories.setVisibility(View.GONE);

        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityIntent);
            }
        });

        calendarDateChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendar.getVisibility() == View.VISIBLE) {
                    calendar.setVisibility(View.GONE);
                    System.out.println(calendar.getVisibility());
                } else {
                    calendar.setVisibility(View.VISIBLE);
                    System.out.println(calendar.getVisibility());
                }
            }
        });

        categoriesChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categories.getVisibility() == View.GONE)
                    categories.setVisibility(View.VISIBLE);
                else
                    categories.setVisibility(View.GONE);
            }
        });

        CategoriesListAdapter categoriesListAdapter = new CategoriesListAdapter(context, dbContext.getAssetTypes());
        categories.setAdapter(categoriesListAdapter);

        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                assetType = (AssetType) categories.getItemAtPosition(position);
                categoryTextInput.setText(assetType.assetType);
                categories.setVisibility(View.GONE);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                purchaseDate = new Date(year, month, dayOfMonth);
                dateTextInput.setText(String.format("%d-%d-%d", purchaseDate.getDate(), purchaseDate.getMonth(), purchaseDate.getYear()));
                calendar.setVisibility(View.GONE);
            }
        });

        addAssetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Asset newAsset = new Asset(
                        assetNameTextInput.getText().toString(),
                        assetType,
                        descriptionTextInput.getText().toString(),
                        purchaseDate,
                        tryParseInt(warrantyTextInput.getText().toString()),
                        tryParseInt(rowTextInput.getText().toString()),
                        tryParseInt(columnTextInput.getText().toString())
                );

                boolean validationSucceeded = newAsset.Validate();

                if (validationSucceeded) {
                    dbContext.tryAddAsset(newAsset);
                    Toast.makeText(context, "Asset " + newAsset.name + " added successfully!", Toast.LENGTH_LONG).show();
                    startActivity(mainActivityIntent);
                } else
                    Toast.makeText(context, "Fail while adding new asset", Toast.LENGTH_LONG).show();
            }
        });
    }

    private int tryParseInt(String stringValue) {
        try {
            return Integer.parseInt(stringValue);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
