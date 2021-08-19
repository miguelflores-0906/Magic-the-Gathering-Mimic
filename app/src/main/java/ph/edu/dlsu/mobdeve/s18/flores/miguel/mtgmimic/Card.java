package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

public class Card {
    private String cardName, set, type;

    public Card(String cardName, String set, String type) {
        this.cardName = cardName;
        this.set = set;
        this.type = type;
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
}
