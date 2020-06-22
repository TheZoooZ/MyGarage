package android.pk.mygarage.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.pk.mygarage.R;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddAssetActivity extends AppCompatActivity {

    private Context context;
    private TextView assetNameTextInput;
    private TextView descriptionTextInput;
    private TextView categoryTextInput;
    private TextView dateTextInput;
    private TextView rowTextInput;
    private TextView columnTextInput;
    private ImageButton categoriesChoiceButton;
    private ImageButton calendarDateChoiceButton;
    private Button addAssetButton;
    private ImageButton backToMainActivityButton;
    private CalendarView calendar;
    private ListView categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);

        context = this;

        assetNameTextInput = findViewById(R.id.assetNameTextInput);
        descriptionTextInput = findViewById(R.id.descriptionTextInput);
        categoryTextInput = findViewById(R.id.categoryTextInput);
        dateTextInput = findViewById(R.id.dateTextInput);
        rowTextInput = findViewById(R.id.rowTextInput);
        columnTextInput = findViewById(R.id.columnTextInput);
        categoriesChoiceButton = findViewById(R.id.categoriesChoiceButton);
        calendarDateChoiceButton = findViewById(R.id.dateChoiceButton);
        addAssetButton = findViewById(R.id.addAssetConfirmationButton);
        backToMainActivityButton = findViewById(R.id.backToMainActivityButton);
        calendar = findViewById(R.id.calendarDateChoiceView);
        categories = findViewById(R.id.categoriesChoiceListView);

        calendar.setVisibility(View.GONE);
        categories.setVisibility(View.GONE);

        backToMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(context, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        calendarDateChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendar.getVisibility() == View.VISIBLE){
                    calendar.setVisibility(View.GONE);
                    System.out.println(calendar.getVisibility());
                }
                else {
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
    }
}
