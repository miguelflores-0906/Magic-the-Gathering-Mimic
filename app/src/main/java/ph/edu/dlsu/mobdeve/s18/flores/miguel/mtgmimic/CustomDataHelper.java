package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                "Legendary Planeswalker"));
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
                "Legendary Planeswalker"));
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
        data.add(new Card(
                "Island",
                "AFR",
                "Land"));
        data.add(new Card(
                "Plains",
                "AFR",
                "Land"));
        data.add(new Card(
                "Mountain",
                "AFR",
                "Land"));
        data.add(new Card(
                "Swamp",
                "AFR",
                "Land"));
        data.add(new Card(
                "Forest",
                "AFR",
                "Land"));
        data.add(new Card(
                "Gigantosaurus",
                "M19",
                "Creature"));
        data.add(new Card(
                "Ajani, Strength of the Pride",
                "M20",
                "Legendary Planeswalker"));
        data.add(new Card(
                "Ugin, the Spirit Dragon",
                "M21",
                "Legendary Planeswalker"));
        data.add(new Card(
                "Teferi, Master of Time",
                "M21",
                "Legendary Planeswalker"));
        data.add(new Card(
                "Teferi, Time Raveler",
                "WAR",
                "Legendary Planeswalker"));
        data.add(new Card(
                "Ajani's Pridemate",
                "M19",
                "Creature"));

        Collections.sort(data, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getCardName().compareTo(o2.getCardName());
            }
        });

        return data;
    }
}
