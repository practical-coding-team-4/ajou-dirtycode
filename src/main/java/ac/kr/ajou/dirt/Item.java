package ac.kr.ajou.dirt;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public boolean isNamed(String name) {
        return name.equals(this.name);
    }

    public void incQuality() {
        if (this.quality < 50)
            this.quality = this.quality + 1;
    }

    public void decQuality() {
        if(this.quality > 0)
            this.quality = this.quality - 1;
    }
}