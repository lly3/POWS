public class CompanyTile {
    private String type;
    private int currentBid;
    private Investor currentInvestor;
    private boolean isClose;
    private boolean isMultiply;

    public CompanyTile(String type) {
        this.type = type;
    }
    public CompanyTile(String type, boolean isMultiply) {
        this.type = type;
        this.isMultiply = isMultiply;
    }

    public void bid(int cost, Investor investor) {
        this.currentBid = cost;
        this.currentInvestor = investor;
    }

    public void setPrice(int cost) {
        this.currentBid = cost;
    }

    public void close() {
        this.isClose = true;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        if(this.isMultiply)
            return this.type + " with 2X";
        return this.type;
    }

    public int getCurrentBid() {
        return this.currentBid;
    }

    public Investor getCurrentInvestor() {
        return this.currentInvestor;
    }

    public boolean getIsMultiply() {
        return this.isMultiply;
    }
}
