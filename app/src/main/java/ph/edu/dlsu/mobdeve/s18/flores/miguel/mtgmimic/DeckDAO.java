package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public interface DeckDAO {
    long addDeck(Deck deck);
    ArrayList<Deck> getDecks();
}
