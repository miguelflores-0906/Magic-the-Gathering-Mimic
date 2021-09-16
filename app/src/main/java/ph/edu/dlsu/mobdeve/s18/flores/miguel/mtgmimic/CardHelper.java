package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.util.List;

import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

public class CardHelper {
    //loads all cards
    List<Card> cards = CardAPI.getAllCards();
}
