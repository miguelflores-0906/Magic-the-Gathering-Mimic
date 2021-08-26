package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public class Deck {

    private String username;
    private String deckname;
    private ArrayList<Card> decklist;

    public Deck(String username, String deckname) {
        this.username = username;
        this.deckname = deckname;
        this.decklist = CustomDataHelper.loadCards();
    }

    public String getUsername() {
        return username;
    }

    public String getDeckname() {
        return deckname;
    }

    public ArrayList<Card> getDecklist() {
        return decklist;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }
}
