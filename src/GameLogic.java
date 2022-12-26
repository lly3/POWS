import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
    private Market market;
    private Investor[] investors;
    private Manager[] managers;
    private ArrayList<CompanyTile> availableTiles;
    private ArrayList<CompanyTile> usedTiles;
    private int R = 5;

    public void initGame() {
        this.market = new Market();
        this.investors = new Investor[]{
                new Investor("Player 1"),
                new Investor("Player 2"),
                new Investor("Player 3")
        };
        this.managers = new Manager[]{
                new Manager("Player 4"),
                new Manager("Player 5")
        };
        this.availableTiles = new ArrayList<CompanyTile>() {
            {
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue"));
                add(new CompanyTile("Blue", true));
                add(new CompanyTile("Blue", true));

                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green"));
                add(new CompanyTile("Green", true));
                add(new CompanyTile("Green", true));

                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow"));
                add(new CompanyTile("Yellow", true));
                add(new CompanyTile("Yellow", true));

                add(new CompanyTile("Red"));
                add(new CompanyTile("Red"));
                add(new CompanyTile("Red"));
                add(new CompanyTile("Red"));
                add(new CompanyTile("Red"));
                add(new CompanyTile("Red"));
                add(new CompanyTile("Red", true));
            }
        };

        this.usedTiles = new ArrayList<CompanyTile>();

        for(int i = 0; i < this.managers.length; i++) {
            this.managers[i].addTile(this.availableTiles.get(i));
            this.managers[i].addTile(this.availableTiles.get(i+13));
            this.managers[i].addTile(this.availableTiles.get(i+26));

            this.usedTiles.add(this.availableTiles.get(i));
            this.usedTiles.add(this.availableTiles.get(i+13));
            this.usedTiles.add(this.availableTiles.get(i+26));

            this.availableTiles.remove(i+26);
            this.availableTiles.remove(i+13);
            this.availableTiles.remove(i);
        }

        for(int i = 0; i < this.R; ++i) {
            System.out.println("+----------------------------------------------------+");
            System.out.println("|                       Round " + i+1 + "                     |");
            System.out.println("+----------------------------------------------------+");

            this.phaseOne();
            this.phaseTwo();
            this.phaseThree();
            this.phaseFour();
            this.phaseFive();
        }
    }

    public String getInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public CompanyTile randomTile() {
        int index = (int)(Math.random()*6);
        return this.availableTiles.get(index);
    }

    public void checkBalance() {
        for(Manager manager: this.managers) {
            System.out.println(manager.getName() + " total balance is " + manager.getBalance() + "K");
        }
        for(Investor investor : this.investors) {
            System.out.println(investor.getName() + " total balance is " + investor.getBalance() + "K");
        }
    }

    public void phaseOne() {
        System.out.println("+--------------------------------+");
        System.out.println("|       Negotiation Phase        |");
        System.out.println("+--------------------------------+");

        this.market.getMarketPrice();
        this.checkBalance();

        boolean settled = false;
        while(!settled) {
            for(Manager manager: this.managers) {
                System.out.println(manager.getName() + " the manager" + " turn ");
                for(CompanyTile companyTile: manager.getCompanyTiles()) {
                    String input = this.getInput("Set the price for (" + companyTile.getName() + ") tile (" + companyTile.getCurrentBid() + "K): ");
                    companyTile.setPrice(Integer.parseInt(input));
                }
            }

            for(Investor investor: this.investors) {
                System.out.println(investor.getName() + " the investor" + " turn ");
                for(CompanyTile companyTile: this.usedTiles) {
                    String bid = this.getInput("Bid for (" + companyTile.getName() + ") company tiles (" + companyTile.getCurrentBid() + "K): ");
                    String settle = this.getInput(investor.getName() + " is bid for " + bid + "K, settle the bid? (Y/N): ");
                    if(settle.equals("Y")) {
                        investor.bid(companyTile, Integer.parseInt(bid));
                        companyTile.close();
                        System.out.println("The bid has been settled!");
                    }
                    else
                        System.out.println("Sorry, your bid is not settle");
                }
            }

            System.out.println("+-------------------------------------------+");
            System.out.println("|             Current Investment            |");
            System.out.println("+-------------------------------------------+");
            System.out.println("|                         |  owner  |  bid  |");
            System.out.println("+-------------------------------------------+");
            for(CompanyTile companyTile: this.usedTiles) {
                System.out.printf("|  %-12s  |  %-10s  |  %4s K  |%n", companyTile.getName(), companyTile.getCurrentInvestor().getName(), companyTile.getCurrentBid());
            }
            System.out.println("+-------------------------------------------+");

            String input = this.getInput("Is negotiate complete? (Y/N): ");
            if(input.equals("Y"))
                settled = true;
        }
    }

    public void phaseTwo() {
        System.out.println("+--------------------------------+");
        System.out.println("|         Investor Income        |");
        System.out.println("+--------------------------------+");

        this.market.update();
        this.market.getMarketPrice();

        for(CompanyTile companyTile: this.usedTiles) {
            if(companyTile.getIsMultiply())
                switch (companyTile.getType()) {
                    case "Blue":
                        companyTile.getCurrentInvestor().income(this.market.checkBlueSharePrice()*2);
                        break;
                    case "Green":
                        companyTile.getCurrentInvestor().income(this.market.checkGreenSharePrice()*2);
                        break;
                    case "Yellow":
                        companyTile.getCurrentInvestor().income(this.market.checkYellowSharePrice()*2);
                        break;
                    case "Red":
                        companyTile.getCurrentInvestor().income(this.market.checkRedSharePrice()*2);
                        break;
                }
            else
                switch (companyTile.getType()) {
                    case "Blue":
                        companyTile.getCurrentInvestor().income(this.market.checkBlueSharePrice());
                        break;
                    case "Green":
                        companyTile.getCurrentInvestor().income(this.market.checkGreenSharePrice());
                        break;
                    case "Yellow":
                        companyTile.getCurrentInvestor().income(this.market.checkYellowSharePrice());
                        break;
                    case "Red":
                        companyTile.getCurrentInvestor().income(this.market.checkRedSharePrice());
                        break;
                }
        }
        this.checkBalance();
    }

    public void phaseThree() {
        System.out.println("+--------------------------------+");
        System.out.println("|          Manager Income        |");
        System.out.println("+--------------------------------+");

        for(Manager manager: this.managers) {
            for(CompanyTile companyTile: manager.getCompanyTiles()) {
                manager.income(companyTile);
                companyTile.getCurrentInvestor().pay(companyTile);
            }
        }
        this.checkBalance();
    }

    public void phaseFour() {
        System.out.println("+--------------------------------+");
        System.out.println("|    Management Costs Payment    |");
        System.out.println("+--------------------------------+");

        for(Manager manager: this.managers) {
            manager.payManagementCosts();
        }
        this.checkBalance();
    }

    public void phaseFive() {
        System.out.println("+--------------------------------+");
        System.out.println("|         Company Auction        |");
        System.out.println("+--------------------------------+");

        CompanyTile companyTile = this.randomTile();

        boolean settled = false;
        Manager leastManager = null;
        int leastBid = 0;
        while(!settled) {
            for (Manager manager : this.managers) {
                System.out.println(manager.getName() + " the manager" + " turn ");
                String bid = this.getInput("Bid for (" + companyTile.getName() + ") company tiles (" + companyTile.getCurrentBid() + "K) (N for no bidding): ");
                int currentBid = Integer.parseInt(bid);
                if(bid.equals("N"))
                    continue;
                if(leastBid < currentBid) {
                    companyTile.setPrice(currentBid);
                    leastManager = manager;
                    leastBid = currentBid;
                }
            }

            String input = this.getInput("Is negotiate complete? (Y/N): ");
            if (input.equals("Y")) {
                settled = true;
                if(leastManager != null) {
                    leastManager.pay(companyTile);
                    leastManager.addTile(companyTile);

                    this.usedTiles.add(companyTile);
                    this.availableTiles.remove(companyTile);
                }
            }
        }
    }
}
