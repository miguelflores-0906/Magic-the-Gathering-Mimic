package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

public class BuilderCard {
    private int multiverseId;
    private String name;
    private int qty;

    public BuilderCard(int multiverseId, String name, int qty) {
        this.multiverseId = multiverseId;
        this.name = name;
        this.qty = qty;
    }

    public int getMultiverseId() {
        return multiverseId;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }
}
