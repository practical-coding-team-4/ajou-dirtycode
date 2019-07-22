package ac.kr.ajou.dirt;

class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if(item.isNamed("Aged Brie")) {
                incrementQualityLessThan50(item);
            } else if (item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
                incrementQualityLessThan50(item);
                if (item.sellIn < 11) {
                    incrementQualityLessThan50(item);
                }
                if (item.sellIn < 6) {
                    incrementQualityLessThan50(item);
                }
            } else if (item.quality > 0){
                degradeQualityExceptSulfuras(item);
            }

            if (!item.isNamed("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (item.isNamed("Aged Brie")) {
                    incrementQualityLessThan50(item);
                } else if(item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else if (item.quality > 0) {
                    degradeQualityExceptSulfuras(item);
                }
            }
        }
    }

    private void incrementQualityLessThan50(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
    private void degradeQualityExceptSulfuras(Item item) {
        if(!item.isNamed("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }
}