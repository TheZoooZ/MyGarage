package android.pk.mygarage.Database;

import android.pk.mygarage.Common.AssetsMock;
import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.Core.Models.AssetType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbContext {
    private static Asset[] assets = {
            AssetsMock.gogle,
            AssetsMock.korkociag,
            AssetsMock.maczeta,
            AssetsMock.metr,
            AssetsMock.noz,
            AssetsMock.pilkaDoMetalu,
            AssetsMock.sruba,
            AssetsMock.wasserWaga,
            AssetsMock.wiertarka,
            AssetsMock.wygluszacze,
    };
    private static AssetType[] assetTypes = {
            AssetsMock.mechaniczne,
            AssetsMock.pomiary,
            AssetsMock.odziez,
            AssetsMock.elektronarzedzia
    };


    private static List<AssetType> assetTypeList = new ArrayList<AssetType>() {{
        addAll(Arrays.asList(assetTypes));
    }};
    private static ArrayList<Asset> assetList = new ArrayList<Asset>() {{
        addAll(Arrays.asList(assets));
    }};

    public List<Asset> getAssets() {
        return assetList;
    }

    public List<Asset> getAssetsByName(String assetName) {
        List<Asset> filteredResult = new ArrayList<>();

        for (Asset asset : assetList)
            if (asset.name.toLowerCase().contains(assetName.toLowerCase()))
                filteredResult.add(asset);

        return filteredResult;
    }

    public List<Asset> getAssetsByType(String assetType){
        List<Asset> filteredResult = new ArrayList<>();

        for(Asset asset : assetList)
            if (asset.type.assetType.equals(assetType))
                filteredResult.add(asset);

        return filteredResult;
    }

    public List<AssetType> getAssetTypes() {
        return assetTypeList;
    }

    public boolean tryAddAsset(Asset asset) {
        if (asset != null) {
            assetList.add(asset);
            return true;
        }

        return false;
    }

    public boolean tryRemoveAsset(int assetId) {
        for (Asset asset : assetList) {
            if (asset.id == assetId) {
                assetList.remove(asset);
                return true;
            }
        }

        return false;
    }
}