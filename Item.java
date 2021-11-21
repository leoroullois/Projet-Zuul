public class Item {
    private String aDescription;
    private double aPrice;

    public Item(final String pDescription, final double pPrice) {
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

    
}
