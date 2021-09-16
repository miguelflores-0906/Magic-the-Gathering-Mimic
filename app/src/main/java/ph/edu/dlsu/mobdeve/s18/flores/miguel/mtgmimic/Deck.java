package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

import io.magicthegathering.javasdk.resource.Card;

public class Deck {

    private String username;
    private String deckname;

    private ArrayList<io.magicthegathering.javasdk.resource.Card> decklist;

    public Deck()
    {

    }

    public Deck(String username, String deckname)
    {
        this.username = username;
        this.deckname = deckname;
    }

    public Deck(String username, String deckname, ArrayList<io.magicthegathering.javasdk.resource.Card> decklist) {
        this.username = username;
        this.deckname = deckname;
        this.decklist = decklist;
    }

    public String getUsername() {
        return username;
    }

    public String getDeckname() {
        return deckname;
    }

    public ArrayList<io.magicthegathering.javasdk.resource.Card> getDecklist() {
        return decklist;
    }

    public void setDeckname(String deckname) {
        this.deckname = deckname;
    }

    public void setUsername(String username){ this.username = username;}

    public void setDecklist(ArrayList<Card> decklist){this.decklist = decklist;}
}
