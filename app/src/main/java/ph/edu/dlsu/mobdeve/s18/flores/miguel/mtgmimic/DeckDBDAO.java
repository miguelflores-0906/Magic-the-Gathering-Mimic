package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public interface DeckDBDAO {
    long addDeck (Deck deck);
    ArrayList<Deck> getDecks();
    ArrayList<Deck> getUserDecks(String username);
}
