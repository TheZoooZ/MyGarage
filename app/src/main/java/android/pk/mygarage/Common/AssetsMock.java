package android.pk.mygarage.Common;

import android.pk.mygarage.Core.Models.Asset;
import android.pk.mygarage.Core.Models.AssetType;

import java.util.GregorianCalendar;

public class AssetsMock {
    public static AssetType elektronarzedzia = new AssetType(1, "Elektronarzędzia");
    public static AssetType mechaniczne = new AssetType(2, "Narzędzia mechaniczne");
    public static AssetType pomiary = new AssetType(3, "Pomiary");
    public static AssetType odziez = new AssetType(4, "Odzież");

    public static Asset wiertarka = new Asset(
            1,
            "Wiertarka",
            elektronarzedzia,
            "Dobra do wiercenia",
            new GregorianCalendar(2020, 05, 02),
            2,
            2,
            2
    );
    public static Asset sruba = new Asset(
            2,
            "Śruba",
            mechaniczne,
            "Średnica 22mm",
            new GregorianCalendar(2020,04,1),
            0,
            3,
            4
    );
    public static Asset noz = new Asset(
            3,
            "Nóż",
            mechaniczne,
            "Bardzo ostry, lepiej uważać.",
            new GregorianCalendar(2020,07,10),
            0,
            4,
            2
    );
    public static Asset korkociag = new Asset(
            4,
            "Korkociąg",
            mechaniczne,
            "Wczoraj otwierałem nim szampana...",
            new GregorianCalendar(2020,1,22),
            4,
            1,
            1
    );
    public static Asset maczeta = new Asset(
            5,
            "Maczeta",
            mechaniczne,
            "Kupiłem od kolegów...",
            new GregorianCalendar(2020,2,2),
            10,
            1,
            5
    );
    public static Asset gogle = new Asset(
            6,
            "Gogle ochronne",
            odziez,
            "Idealnie komponują się z maczetą",
            new GregorianCalendar(2020,2,3),
            2,
            6,
            9
    );
    public static Asset pilkaDoMetalu = new Asset(
            7,
            "Piłka do metalu",
            mechaniczne,
            "Żaden metal się jej nie oprze",
            new GregorianCalendar(2020,01,12),
            2,
            4,2
    );
    public static Asset wygluszacze = new Asset(
            8,
            "Wygłuszacze do uszu",
            odziez,
            "Nie straszny im żaden hałas!",
            new GregorianCalendar(2020,01,29),
            0,
            2,10
    );
    public static Asset wasserWaga = new Asset(
            9,
            "Poziomica",
            pomiary,
            "Pomimo swojego wieku ciągle trzyma poziom.",
            new GregorianCalendar(1990,12,12),
            0,
            10,
            10
    );
    public static Asset metr = new Asset(
            10,
            "Metr 25m",
            pomiary,
            "Taka długość zadowoliłaby niejednego mężczyznę",
            new GregorianCalendar(2020,05,18),
            4,
            4,8
    );
}
