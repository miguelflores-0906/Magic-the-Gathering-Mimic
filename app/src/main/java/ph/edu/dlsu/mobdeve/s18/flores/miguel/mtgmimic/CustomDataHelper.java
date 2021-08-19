package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public class CustomDataHelper {
    public static ArrayList<Card> loadCards() {
        ArrayList<Card> data = new ArrayList<>();

        data.add(new Card(
                "Evolving Wilds",
                "BFZ",
                "Land"));
        data.add(new Card(
                "Ob Nixilis Reignited",
                "BFZ",
                "Planeswalker"));
        data.add(new Card(
                "Opt",
                "M21",
                "Instant"));
        data.add(new Card(
                "Professor Onyx",
                "STX",
                "Planeswalker"));
        data.add(new Card(
                "Ruin Crab",
                "ZNR",
                "Creature"));
        data.add(new Card(
                "Elite Spellbinder",
                "STX",
                "Creature"));
        data.add(new Card(
                "Guiding Voice",
                "STX",
                "Sorcery"));
        data.add(new Card(
                "Teferi's Tutelage",
                "M21",
                "Enchantment"));
        data.add(new Card(
                "Ashiok, Nightmare Muse",
                "THB",
                "Planeswalker"));
        data.add(new Card(
                "Pacifism",
                "IKR",
                "Enchantment"));
        data.add(new Card(
                "Embercleave",
                "ELD",
                "Legendary Artifact"));
        data.add(new Card(
                "The Tarrasque",
                "AFR",
                "Legendary Creature"));

        return data;
    }
}
