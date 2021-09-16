package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public class DeckHelper {
    public static ArrayList<Deck> loadDecks() {
        ArrayList<Deck> data = new ArrayList<>();

        data.add(new Deck(
                "Narwhal_",
                "Grixis Control",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Mono Green Stompy",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Abzan Dungeon Crawl",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Izzet Spells",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Dimir Mill",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Sphynx Tutelage Mill",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Selesnya Cats Tribal",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Mardu Goblins",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Orzhov Clerics",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Mono Black Reanimator",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Colorless Myr",
                CustomDataHelper.loadCards()
        ));
        data.add(new Deck(
                "Narwhal_",
                "Ugin lol",
                CustomDataHelper.loadCards()
        ));

        return data;
    }

}
