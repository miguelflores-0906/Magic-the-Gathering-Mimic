package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public class StatsHelper {
    public static ArrayList<Stats> loadStats() {
        ArrayList<Stats> stats = new ArrayList<>();

        stats.add(new Stats(
            "This card is in 50% of your decks",
                R.drawable.stats_one
        ));
        stats.add(new Stats(
                "This card is in 25% of other user's decks",
                R.drawable.stats_two
        ));
        stats.add(new Stats(
                "Makes up 1% of your collection",
                R.drawable.stats_three
        ));

        return stats;
    }
}
