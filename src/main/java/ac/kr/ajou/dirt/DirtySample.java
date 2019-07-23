package ac.kr.ajou.dirt;

public class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQualityAndSellin() {
        for (int i = 0; i < items.length; i++) {
            if(items[i].name.equals("Sulfuras, Hand of Ragnaros")) continue;

            if(isAgedBrie(items[i])) {
                increaseQualityBefore50(items[i]);

            }else if(isBackstagePassesForTAFKAL80ETC(items[i])){
                increaseQualityBefore50(items[i]);
                increaseQualityAccordingToSellIn(items[i]);

            }else{
                decreaseQuality(items[i]);
            }

            items[i].sellIn = items[i].sellIn - 1;
            if (items[i].sellIn < 0) {
                modifyQualityWhenSellinLowerThan0(items[i]);
            }
        }
    }

    private void modifyQualityWhenSellinLowerThan0(Item item) {
        if(isAgedBrie(item)){
            increaseQualityBefore50(item);
        }else if(isBackstagePassesForTAFKAL80ETC(item)){
            item.quality = 0;
        }else{
            decreaseQuality(item);
        }
    }

    private void increaseQualityAccordingToSellIn(Item item) {
        if (item.sellIn < 11) {
            increaseQualityBefore50(item);
        }
        if (item.sellIn < 6) {
            increaseQualityBefore50(item);
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePassesForTAFKAL80ETC(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQualityBefore50(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}


//1. SRP -> 하나의 클래스의 하나의 책임 만족
//2. 중복되는 코드 extract 해줌.
//3. 함수 이름 처음 봤을 때 최대한 이해가 되게 만듦.
//3. 'Sulfuras, Hand of Ragnaros'일 때 아무 변경도 없으므로 이를 빼면
// 'Aged Brie', 'Backstage passes to a TAFKAL80ETC concert', '나머지' 크게 3가지 경우로 나눌 수 있었음.
//4. 14~23번째 if-else 라인은 이해하는데 크게 어려움이 없다고 생각해서 따로 빼지 않음.