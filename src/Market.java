public class Market {
    private Share blueShare;
    private Share greenShare;
    private Share yellowShare;
    private Share redShare;
    private Dice blueDice;
    private Dice greenDice;
    private Dice yellowDice;
    private Dice redDice;

    public Market() {
        this.blueShare = new Share("Blue");
        this.blueDice = new Dice("Blue");
        this.greenShare = new Share("Green");
        this.greenDice = new Dice("Green");
        this.yellowShare = new Share("Yellow");
        this.yellowDice = new Dice("Yellow");
        this.redShare = new Share("Red");
        this.redDice = new Dice("Red");
    }

    public int checkBlueSharePrice() {
        return this.blueShare.getValue();
    }

    public int checkGreenSharePrice() {
        return this.greenShare.getValue();
    }

    public int checkYellowSharePrice() {
        return this.yellowShare.getValue();
    }

    public int checkRedSharePrice() {
        return this.redShare.getValue();
    }

    public void update() {
        this.blueShare.setPosition(this.blueDice.roll());
        this.greenShare.setPosition(this.greenDice.roll());
        this.yellowShare.setPosition(this.yellowDice.roll());
        this.redShare.setPosition(this.redDice.roll());
    }

    public void getMarketPrice() {
        System.out.println("+---------------------------+");
        System.out.println("|        Market Price       |");
        System.out.println("|---------------------------|");
        System.out.println("| Blue   share is now: " + this.checkBlueSharePrice() + "K! |");
        System.out.println("| Green  share is now: " + this.checkGreenSharePrice() + "K! |");
        System.out.println("| Yellow share is now: " + this.checkYellowSharePrice() + "K! |");
        System.out.println("| Red    share is now: " + this.checkRedSharePrice() + "K! |");
        System.out.println("+---------------------------+");
    }
}
