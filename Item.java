public class Item {
    private String aName;
    private String aDescription;
    private double aPrice;

    public Item(final String pName, final String pDescription, final double pPrice) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aPrice = pPrice;
    }

    // Getters et setters
    public String getDescription() {
        return this.aDescription;
    }

    public void setDescription(final String pDescription) {
        this.aDescription = pDescription;
    }

    public double getPrice() {
        return this.aPrice;
    }

    public void setPrice(final double pPrice) {
        this.aPrice = pPrice;
    }

    public String getName() {
        return this.aName;
    }

    public String getItemString(final String pName) {
        return this.aDescription;
    }
}
