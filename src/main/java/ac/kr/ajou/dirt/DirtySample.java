package ac.kr.ajou.dirt;

public class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isNameNotEquals(item, "Aged Brie")
                    && isNameNotEquals(item, "Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    changeQuality(item, isNameNotEquals(item, "Sulfuras, Hand of Ragnaros"), item.quality - 1);
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (isNameEquals(item)) {
                        if (item.sellIn < 11) {
                            changeQuality(item, item.quality < 50, item.quality + 1);
                        }

                        if (item.sellIn < 6) {
                            changeQuality(item, item.quality < 50, item.quality + 1);
                        }
                    }
                }
            }

            if (isNameNotEquals(item, "Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (isNameNotEquals(item, "Aged Brie")) {
                    if (isNameNotEquals(item, "Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > 0) {
                            changeQuality(item, isNameNotEquals(item, "Sulfuras, Hand of Ragnaros"), item.quality - 1);
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    changeQuality(item, item.quality < 50, item.quality + 1);
                }
            }
        }
    }

    private void changeQuality(Item item, boolean b, int i) {
        if (b) {
            item.quality = i;
        }
    }

    private boolean isNameEquals(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isNameNotEquals(Item item, String s) {
        return !item.name.equals(s);
    }
}