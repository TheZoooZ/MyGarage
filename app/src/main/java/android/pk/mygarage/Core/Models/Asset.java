package android.pk.mygarage.Core.Models;

import java.io.Serializable;
import java.util.Date;

public class Asset implements Serializable {
    public int id;
    public String name;
    public AssetType type;
    public String description;
    public Date purchaseDate;
    public int yearsOfWarranty;
    public int row;
    public int column;

    public Asset(int id, String name, AssetType type, String description, Date purchaseDate, int yearsOfWarranty, int row, int column) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.yearsOfWarranty = yearsOfWarranty;
        this.row = row;
        this.column = column;
    }

    public Asset(String name, AssetType type, String description, Date purchaseDate, int yearsOfWarranty, int row, int column) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.yearsOfWarranty = yearsOfWarranty;
        this.row = row;
        this.column = column;
    }

    public boolean Validate() {
        if (name == null || name.isEmpty())
            return false;
        if (type == null)
            return false;
        if (row == 0)
            return false;
        if (column == 0)
            return false;
        return true;
    }
}
