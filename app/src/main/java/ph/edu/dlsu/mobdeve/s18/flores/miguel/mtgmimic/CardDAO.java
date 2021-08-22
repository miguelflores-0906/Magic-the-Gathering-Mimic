package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.ArrayList;

public interface CardDAO {

    long addCard(Card card);
    ArrayList<Card> getCards();
}
