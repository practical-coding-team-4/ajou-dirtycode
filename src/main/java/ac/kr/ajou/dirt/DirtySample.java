package ac.kr.ajou.dirt;

public class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (isNameNotEquals(items[i], "Aged Brie")
                    && isNameNotEquals(items[i], "Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (isNameNotEquals(items[i], "Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (isNameEquals(items[i])) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (isNameNotEquals(items[i], "Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (isNameNotEquals(items[i], "Aged Brie")) {
                    if (isNameNotEquals(items[i], "Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (isNameNotEquals(items[i], "Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }

    private boolean isNameEquals(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isNameNotEquals(Item item, String s) {
        return !item.name.equals(s);
    }
}