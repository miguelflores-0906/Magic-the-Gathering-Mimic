package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public class DeckHelper {
    public static ArrayList<Deck> loadDecks() {
        ArrayList<Deck> data = new ArrayList<>();

        data.add(new Deck(
                "Narwhal_",
                "Grixis Control"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Mono Green Stompy"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Abzan Dungeon Crawl"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Izzet Spells"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Dimir Mill"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Sphynx Tutelage Mill"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Selesnya Cats Tribal"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Mardu Goblins"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Orzhov Clerics"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Mono Black Reanimator"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Colorless Myr"
        ));
        data.add(new Deck(
                "Narwhal_",
                "Ugin lol"
        ));

        return data;
    }

}
