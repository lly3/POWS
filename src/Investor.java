public class Investor {
    private int investmentToken;
    private int balance;

    private String name;

    public Investor(String name) {
        this.investmentToken = 10;
        this.balance = 120;
        this.name = name;
    }

    public void income(int income) {
        this.balance += income;
    }

    public void bid(CompanyTile companyTile, int cost) {
        companyTile.bid(cost, this);
    }

    public void pay(CompanyTile companyTile) {
        this.balance -= companyTile.getCurrentBid();
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }
}
