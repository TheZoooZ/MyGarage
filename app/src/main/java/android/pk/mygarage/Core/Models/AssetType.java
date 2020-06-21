package android.pk.mygarage.Core.Models;

import java.io.Serializable;

public class AssetType implements Serializable {
    public int id;
    public String assetType;

    public AssetType(int id, String assetType) {
        this.id = id;
        this.assetType = assetType;
    }
}