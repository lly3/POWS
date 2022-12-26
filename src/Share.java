public class Share {
    private int[] values;
    private int position;

    public Share(String type) {
        this.position = 3;
        switch (type) {
            case "Blue":
                this.values = new int[]{20,20,20,30,30,30,40,40};
                break;
            case "Green":
                this.values = new int[]{0,10,20,30,30,40,50,60};
                break;
            case "Yellow":
                this.values = new int[]{10,0,0,30,40,40,60,60};
                break;
            case "Red":
                this.values = new int[]{-20,-10,0,30,40,50,60,70};
                break;
        }
    }

    public int getValue() {
        return this.values[this.position];
    }

    public void setPosition(int position) {
        if(position + this.position > 5)
            this.position = 5;
        else if(position + this.position < 0)
            this.position = 0;
        else
            this.position += position;
    }
}
