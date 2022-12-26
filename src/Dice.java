public class Dice {
    private int[] faces;

    public Dice(String type) {
        switch (type) {
            case "Blue":
                this.faces = new int[]{-1,-1,0,0,1,1};
                break;
            case "Green":
                this.faces = new int[]{-2,-1,0,0,1,2};
                break;
            case "Yellow":
                this.faces = new int[]{-3,-2,1,1,2,3};
                break;
            case "Red":
                this.faces = new int[]{-7,-3,1,1,3,7};
                break;
        }
    }

    public int roll() {
        return faces[(int)(Math.random()*6)];
    }
}
