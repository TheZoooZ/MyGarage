package android.pk.mygarage.Database;

import android.pk.mygarage.Common.AssetsMock;
import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.Core.Models.AssetType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DbContext {
    private final String ApiUrl = "https://mygarageapi.azurewebsites.net/api";

    private static List<AssetType> assetTypes = new ArrayList<AssetType>() {{
        add(AssetsMock.mechaniczne);
        add(AssetsMock.pomiary);
        add(AssetsMock.odziez);
        add(AssetsMock.elektronarzedzia);
    }};

    private static ArrayList<Asset> assets = new ArrayList<Asset>() {{
        add(AssetsMock.gogle);
        add(AssetsMock.korkociag);
        add(AssetsMock.maczeta);
        add(AssetsMock.metr);
        add(AssetsMock.noz);
        add(AssetsMock.pilkaDoMetalu);
        add(AssetsMock.sruba);
        add(AssetsMock.wasserWaga);
        add(AssetsMock.wiertarka);
        add(AssetsMock.wygluszacze);
    }};

    public List<Asset> getAssets() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = readJsonFromUrl(ApiUrl + "/Assets").toString();
            return objectMapper.readValue(jsonString, new TypeReference<List<Asset>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return assets;
    }

    public List<Asset> getAssetsByName(String assetName) {
        List<Asset> assets;
        List<Asset> filteredResult = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = readJsonFromUrl(ApiUrl + "/Assets").toString();
            assets = objectMapper.readValue(jsonString, new TypeReference<List<Asset>>() {
            });
        } catch (IOException e) {
            assets = DbContext.assets;
            e.printStackTrace();
        } catch (JSONException e) {
            assets = DbContext.assets;
            e.printStackTrace();
        }

        for (Asset asset : assets)
            if (asset.name.toLowerCase().contains(assetName.toLowerCase()))
                filteredResult.add(asset);
        return filteredResult;
    }

    public List<Asset> getAssetsByType(String assetType) {
        List<Asset> assets;
        List<Asset> filteredResult = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonString = readJsonFromUrl(ApiUrl + "/Assets").toString();
            assets = objectMapper.readValue(jsonString, new TypeReference<List<Asset>>() {
            });
        } catch (IOException e) {
            assets = DbContext.assets;
            e.printStackTrace();
        } catch (JSONException e) {
            assets = DbContext.assets;
            e.printStackTrace();
        }

        for (Asset asset : assets)
            if (asset.type.assetType.equals(assetType))
                filteredResult.add(asset);

        return filteredResult;
    }

    public List<AssetType> getAssetTypes() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = readJsonFromUrl(ApiUrl + "/AssetTypes").toString();
            return objectMapper.readValue(jsonString, new TypeReference<List<AssetType>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return assetTypes;
    }

    public boolean tryAddAsset(Asset asset) {
        if (asset != null) {
            assets.add(asset);
            return true;
        }
        return false;
    }

    public boolean tryRemoveAsset(int assetId) {
        for (Asset asset : assets) {
            if (asset.id == assetId) {
                assets.remove(asset);
                return true;
            }
        }
        return false;
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(reader);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            inputStream.close();
        }
    }

    private static String readAll(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        int capacity;

        while ((capacity = reader.read()) != -1)
            builder.append((char) capacity);

        return builder.toString();
    }
}