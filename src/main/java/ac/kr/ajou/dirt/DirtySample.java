package ac.kr.ajou.dirt;

class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items) {
            if (!item.isNamed("Aged Brie")
                    && !item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.isNamed("Sulfuras, Hand of Ragnaros")) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
                            upgradeQualityUntil50(item);
                        }

                        if (item.sellIn < 6) {
                            upgradeQualityUntil50(item);
                        }
                    }
                }
            }

            if (!item.isNamed("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.isNamed("Aged Brie")) {
                    if (!item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            if (!item.isNamed("Sulfuras, Hand of Ragnaros")) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    upgradeQualityUntil50(item);
                }
            }
        }
    }

    private void upgradeQualityUntil50(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}