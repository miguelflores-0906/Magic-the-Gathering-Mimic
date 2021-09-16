package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class User {
    private String key;
    private String username;
    private String email;
    private ArrayList<Card> ownedCards;

    public User()
    {

    }

    public User(String username, String email, ArrayList<Card> ownedCards, String key)
    {
        this.key = key;
        this.email = email;
        this.ownedCards = ownedCards;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
