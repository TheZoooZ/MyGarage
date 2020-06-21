package android.pk.mygarage;

import android.content.Context;
import android.pk.mygarage.Core.Models.AssetType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CategoriesListAdapter extends ArrayAdapter<AssetType> {
    private Context context;
    private List<String> assetTypes;

    public CategoriesListAdapter(Context context, List<AssetType> assetTypes) {
        super(context, R.layout.categories_list_view, R.id.assetTypeTextView, assetTypes);

        this.context = context;
        this.assetTypes = getAssetTypes(assetTypes);
    }

    private List<String> getAssetTypes(List<AssetType> assetTypes) {
        List<String> result = new ArrayList<>();

        for(AssetType assetType : assetTypes)
            result.add(assetType.assetType);

        return result;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.categories_list_view, parent, false);
        TextView category = row.findViewById(R.id.assetTypeTextView);

        category.setText(assetTypes.get(position));

        return row;
    }
}
