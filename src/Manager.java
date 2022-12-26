import java.util.ArrayList;

public class Manager {
    private int balance;
    private ArrayList<CompanyTile> companyTiles;

    private String name;

    public Manager(String name) {
        this.balance = 120;
        this.name = name;
        this.companyTiles = new ArrayList<CompanyTile>();
    }

    public void addTile(CompanyTile companyTile) {
        this.companyTiles.add(companyTile);
    }

    public void closeBid(CompanyTile companyTile) {
        companyTile.close();
    }

    public void payManagementCosts() {
        this.balance -= companyTiles.size()*10;
    }

    public ArrayList<CompanyTile> getCompanyTiles() {
        return this.companyTiles;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public void income(CompanyTile companyTile) {
        this.balance += companyTile.getCurrentBid();
    }

    public void pay(CompanyTile companyTile) {
        this.balance -= companyTile.getCurrentBid();
    }
}
