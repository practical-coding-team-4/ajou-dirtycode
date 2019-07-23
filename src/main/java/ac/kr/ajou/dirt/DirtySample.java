package ac.kr.ajou.dirt;

public class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateItemsValue() {
        for (Item item : items) {
            if(item.isNamed("Sulfuras, Hand of Ragnaros"))
                continue;

            calculateQuality(item);
            calculateSellIn(item);
            if (item.sellIn < 0) {
                calculateExpired(item);
            }
        }
    }

    private void calculateQuality(Item item) {
        if(item.isNamed("Aged Brie")) {
            item.incQuality();
        } else if (item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
            item.incQuality();
            if (item.sellIn < 11) {
                item.incQuality();
            }
            if (item.sellIn < 6) {
                item.incQuality();
            }
        } else {
            item.decQuality();
        }
    }

    private void calculateSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void calculateExpired(Item item) {
        if (item.isNamed("Aged Brie")) {
            item.incQuality();
        } else if(item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            item.decQuality();
        }
    }
}