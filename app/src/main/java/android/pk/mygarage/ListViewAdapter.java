package android.pk.mygarage;

import android.content.Context;
import android.pk.mygarage.Core.Models.Asset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Asset> {
    private Context context;
    private List<String> assetNames;
    private List<String> assetDescriptions;

    public ListViewAdapter(Context context, List<Asset> assets) {
        super(context, R.layout.row_list_view, R.id.AssetNameTextView, assets);

        this.context = context;
        this.assetNames = getAssetNames(assets);
        this.assetDescriptions = getAssetDescriptions(assets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.row_list_view, parent, false);
        TextView assetName = row.findViewById(R.id.AssetNameTextView);
        TextView assetDescription = row.findViewById(R.id.AssetDescriptionTextView);

        assetName.setText(assetNames.get(position));
        assetDescription.setText(assetDescriptions.get(position));

        return row;
    }

    private List<String> getAssetDescriptions(List<Asset> assets) {
        List<String> assetDescriptions = new ArrayList<>();
        for (Asset asset : assets)
            assetDescriptions.add(asset.description);
        return assetDescriptions;
    }

    private List<String> getAssetNames(List<Asset> assets) {
        List<String> assetNames = new ArrayList<>();
        for (Asset asset : assets)
            assetNames.add(asset.name);
        return assetNames;
    }
}