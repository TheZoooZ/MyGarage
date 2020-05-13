package android.pk.mygarage.Database;

import android.pk.mygarage.Models.Asset;
import android.pk.mygarage.Models.AssetType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DbContext {
    private static String connectionString = "jdbc:sqlserver://falarz.database.windows.net:1433;database=MyGarageDb;user=tkfalarz@falarz;password=TFalarz12;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public Connection dbConnection = null;
    private Statement statement = null;


    public DbContext() {
        try {
            dbConnection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<Asset> GetAssets() {
        List<Asset> assets = null;
        String query =
                "SELECT " +
                        "Assets.[Id], " +
                        "[Name], " +
                        "[TypeId], " +
                        "[AssetTypes].[AssetType], " +
                        "[Description], [PurchaseDate], " +
                        "[YearsOfWarranty], " +
                        "[Row], " +
                        "[Column] " +
                        "FROM Assets, AssetTypes " +
                        "WHERE Assets.TypeId = AssetTypes.Id;";

        try {
            statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(query);
            Asset asset = null;
            while (result.next()) {
                asset = new Asset();
                asset.Id = result.getInt("Id");
                asset.Name = result.getString("Name");
                asset.Type.Id = result.getInt("TypeId");
                asset.Type.AssetType = result.getString("AssetType");
                asset.Description = result.getString("Description");
                asset.PurchaseDate = result.getDate("PurchaseDate");
                asset.YearsOfWarranty = result.getInt("YearsOfWarranty");
                asset.Row = result.getInt("Row");
                asset.Column = result.getInt("Column");

                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    public Asset GetAllAssetInfo(int assetId) {
        Asset asset = new Asset();
        String query =
                "SELECT " +
                        "Assets.[Id], " +
                        "[Name], " +
                        "[TypeId], " +
                        "[AssetTypes].[AssetType], " +
                        "[Description], [PurchaseDate], " +
                        "[YearsOfWarranty], " +
                        "[Row], " +
                        "[Column] " +
                        "FROM Assets, AssetTypes " +
                        "WHERE Assets.TypeId = AssetTypes.Id" +
                        "AND Assets.[Id] = " + assetId;

        try {
            statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                asset.Id = result.getInt("Id");
                asset.Name = result.getString("Name");
                asset.Type.Id = result.getInt("TypeId");
                asset.Type.AssetType = result.getString("AssetType");
                asset.Description = result.getString("Description");
                asset.PurchaseDate = result.getDate("PurchaseDate");
                asset.YearsOfWarranty = result.getInt("YearsOfWarranty");
                asset.Row = result.getInt("Row");
                asset.Column = result.getInt("Column");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asset;
    }

    public List<Asset> GetAssetsByType(int assetTypeId) {
        List<Asset> assets = null;
        String query =
                "SELECT " +
                        "Assets.[Id], " +
                        "[Name], " +
                        "[TypeId], " +
                        "[AssetTypes].[AssetType], " +
                        "[Description], [PurchaseDate], " +
                        "[YearsOfWarranty], " +
                        "[Row], " +
                        "[Column] " +
                        "FROM " +
                        "Assets," +
                        "AssetTypes " +
                        "WHERE " +
                        "Assets.TypeId = AssetTypes.Id" +
                        "AND AssetTypes.[Id] = " + assetTypeId;

        try {
            statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Asset asset = new Asset();
                asset.Id = result.getInt("Id");
                asset.Name = result.getString("Name");
                asset.Type.Id = result.getInt("TypeId");
                asset.Type.AssetType = result.getString("AssetType");
                asset.Description = result.getString("Description");
                asset.PurchaseDate = result.getDate("PurchaseDate");
                asset.YearsOfWarranty = result.getInt("YearsOfWarranty");
                asset.Row = result.getInt("Row");
                asset.Column = result.getInt("Column");

                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    public List<AssetType> GetAssetTypes() {
        List<AssetType> assetTypes = null;
        String query = "SELECT * FROM AssetTypes";

        try {
            statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                AssetType assetType = new AssetType();
                assetType.Id = result.getInt("Id");
                assetType.AssetType = result.getString("AssetType");
                assetTypes.add(assetType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetTypes;
    }
}