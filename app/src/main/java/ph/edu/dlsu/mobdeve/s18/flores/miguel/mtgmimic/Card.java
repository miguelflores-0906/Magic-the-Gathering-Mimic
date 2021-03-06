package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import java.io.Serializable;

public class Card implements Serializable {
    private String cardName, set, type, rarity, key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Card()
    {

    }


    public Card(String cardName, String set, String type) {
        this.cardName = cardName;
        this.set = set;
        this.type = type;
        this.rarity = rarity;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
