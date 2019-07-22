# ajou refactoring assignment
Code refactored by team 4 in Practical Coding lecture at Ajou Univ.

## Dirty code analysis
- 슥성된 치즈
- 설퍼러스
- TAFKAL80ETC 콘서트 백스테이지 패스

위 세 종류의 아이템을 확인하는데,
1. 치즈가 아니고 패스가 아닌 경우, 아이템의 quality가 양수라면
아이템이 설퍼러스인지 확인하고 맞아면 quality를 하나 감소시킨다.
2. 혹은 만약 quality가 50보다 낮은 경우, quality를 1 증가시킨 후, 패스인 경우,
     1. sellIn이 11보다 작고, quality가 50보다 낮다면 quality를 1 올린다.
     2. sellIn이 6보다 작고, quality가 50보다 낮다면 quality를 1 올린다. 
3. 만약 설퍼러스가 아니라면 sellIn을 1 감소시킨다.
4. 만약 sellIn이 음수이며,
    1. 아이템이 치즈가 아니며, 패스도 아니며, 아이템의 quality가 양수고, 아이템이
    설퍼러스가 아니라면 아이템의 quality를 1 감소시킨다.
    2. 혹은 패스가 맞다면, 아이템의 quality를 0으로 만든다.
    3. 혹은 치즈이며, quality가 50보다 낮다면, item의 가치를 1 증가시킨다.
    
## Refactoring
#### 1. 상수 치환
```$xslt
items[i].quality - items[i].quality = 0;
```
#### 2. 반복적으로 사용되는 method 추출
```$xslt
private void upgradeQualityUntil50(Item item) {
    if (item.quality < 50) {
        item.quality = item.quality + 1;
    }
}
```
#### 3. Foreach문 사용
###### Before
```$xslt
for (int i = 0; i < items.length; i++) {
```
###### After
```$xslt
for (Item item : items) {
```
#### 4. Item에 이름 확인하는 기능 추가
Item의 종류를 확인하기 위해서 이름만을 확인하고 있고 있으며 자주 사용되기 때문에 
Item 객체 내에 아이템 종류를 확인하는 isNamed을 추가한다.
###### Before 
```$xslt
if (!item.name.equals("Aged Brie")
```
###### After 
```$xslt
if (!item.isNamed("Aged Brie")
```
#### 5. 불필요한 Not(!) 연산 지우며 조건문 단순화
Not 연산으로 확인하는 코드를 없애고 if else 문으로 병치하여 읽기 쉽게 정리한다.
###### Before 
```$xslt
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
    incrementQualityLessThan50(item);
}
```
###### After 
```$xslt
if (item.isNamed("Aged Brie")) {
    incrementQualityLessThan50(item);
} else if(item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
    item.quality = 0;
} else if (item.quality > 0) {
    if(!item.isNamed("Sulfuras, Hand of Ragnaros")) {
        item.quality = item.quality - 1;
    }
}
```
## 중간 점검
코드가 29줄로 줄어들 정도로 리팩토링을 어느정도 진행하고 보니 코드의 목적을 파악할 수 있었다. 

모든 아이템은 팔 수 있는 유통기간(sellIn)과 품질(quality)를 가진다.
아이템은 updateQuality 함수가 불릴 때마다 sellIn이 감소하는 것으로 보아,
해당 함수는 하루에 한번 불리며 당일 아이템의 품질을 계산하는 함수로 파악된다.
1. 아이템의 품질이 계산된다.
2. 아이템의 유통기한이 갱신된다.
3. 갱신된 유통기한을 바탕으로 아이템의 품질을 다시 한번 계산한다.

(인위적으로 설정되지 않았다면 아이템의 최고 품질은 50, 최저 품질은 0으로 보인다)

특별한 아이템의 종류는 3종류가 있는데 그 특징은 다음과 같다. 
1. Brie 치즈는 날마다 품질이 숙성되어 품질이 증가하며,
 오히려 유통기한이 지나면 더욱 숙성되는지 품질이 한 단계 더 증가한다.
2. Sulfuras는 전설적인 무기라는 설정 답게 유통기한이 감소하지 않으며,
 시간에 의해 가치가 낮아지지 않는다.
3. 타우렌밴드 콘서트 티켓은 콘서트가 다가 올 수록 품질이 빠르게 증가하지만,
 유통기한이 지나는 순간 가치가 사라진다(0이 된다).
4. 그 외의 일반적인 아이템은 날이 갈 수록 가치가 하나씩 낮아지고,
 유통 기한이 지나면 하나씩 더 낮아진다.
 
## Refactoring (cont'd)
이제 부터는 파악한 flow를 바탕으로 리팩토링한다.
#### 1. Sulfuras는 변경되지 않는다.
초기에 아이템이 Sulfuras인 경우 다음 아이템으로 속행하여,
 성능, 코드 구조상에 이점을 얻을 수 있다.
```$xslt
if(item.isNamed("Sulfuras, Hand of Ragnaros"))
    continue;
```
#### 2. 반복적으로 사용되는 method 추출
Sulfuras 확인이 사라지니 추출할 수 있는 method가 눈에 띈다.
```$xslt
private void degradeQualityMoreThan0(Item item) {
    if (item.quality > 0) {
        item.quality = item.quality - 1;
    }
}
```

#### 3. 함수 Flow에 따라 method 추출
1. calculateQuality: 다음 quality를 계산
2. calculateSellIn: 다음 sellIn을 계산
3. calculateExpired: sellIn이 음수일 경우 quality 다시 계산

#### 4. (고민 필요) 아이템 quality변경 메소드를 아이템 클래스로 위임
JAVA의 OOP 이념에 따르면 quality 최소, 최대 값을 고려하는 코드는 Item 클래스 자체가
 알고 있도록 하는 것이 맞다고 생각한다. 해당 Item 클래스가 다르게 사용될 경우도 있다면
 불필요한 착업이지만 해당 코드에서는 용도가 하나만 있으므로 위임하도록 한다.

## TODO
- private data와 getter setter 사용
- lombok constructor 사용
- stream 사용 고려

 

