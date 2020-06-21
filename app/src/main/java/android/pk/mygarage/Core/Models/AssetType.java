package android.pk.mygarage.Core.Models;

import java.io.Serializable;

public class AssetType implements Serializable {
    public int Id;
    public String assetType;

    public AssetType(int id, String assetType) {
        Id = id;
        this.assetType = assetType;
    }
}