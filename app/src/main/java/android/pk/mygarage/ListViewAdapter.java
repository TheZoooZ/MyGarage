package android.pk.mygarage;

import android.content.Context;
import android.pk.mygarage.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListViewAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] assetNames;
    private String[] assetDescriptions;

    public ListViewAdapter (Context context, String[] assetNames, String[] assetDescriptions){
        super(context, R.layout.row_list_view, R.id.AssetNameTextView, assetNames);

        this.context = context;
        this.assetNames = assetNames;
        this.assetDescriptions = assetDescriptions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.row_list_view, parent, false);
        TextView assetName = row.findViewById(R.id.AssetNameTextView);
        TextView assetDescription = row.findViewById(R.id.AssetDescriptionTextView);

        assetName.setText(assetNames[position]);
        assetDescription.setText(assetDescriptions[position]);

        return row;
    }
}
