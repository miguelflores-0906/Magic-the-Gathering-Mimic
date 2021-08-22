package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public class User {
    private String email;
    private ArrayList<Card> ownedCards;

    public User()
    {

    }

    public User(String email, int id, ArrayList<Card> ownedCards)
    {
        this.email = email;
        this.ownedCards = ownedCards;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Card> getOwnedCards() {
        return ownedCards;
    }

    public void setOwnedCards(ArrayList<Card> ownedCards) {
        this.ownedCards = ownedCards;
    }
}
