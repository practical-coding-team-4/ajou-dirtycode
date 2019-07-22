package ac.kr.ajou.dirt;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class DirtySampleTest {
    Item[] items = new Item[4];

    @Before
    public void initTest_아이템이름0번째부터3번째까지_sellin과quality는0으로_초기화(){
        items[0] = new Item("Aged Brie", 0, 0);
        items[1] = new Item("Backstage passes to a TAFKAL80ETC concert", 0 , 0);
        items[2] = new Item("Sulfuras, Hand of Ragnaros", 0 , 0);
        items[3] = new Item("other name", 0 , 0);
    }

    @Test
    public void 아이템이름이_2번째가아니면_sellin을1감소(){
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();

        assertThat(items[0].sellIn, is(-1));
        assertThat(items[1].sellIn, is(-1));
        assertThat(items[2].sellIn, is(0));
        assertThat(items[3].sellIn, is(-1));
    }

    @Test
    public void 이름이_0번째일경우_퀄리티가50미만이면1증가__이후sellin이0미만이되면_퀄리티가여전히50미만일때1증가(){
        items[0].quality = 30;
        DirtySample dirtySample1 = new DirtySample(items);
        dirtySample1.updateQuality();
        assertThat(items[0].quality, is(32));

        items[0].quality = 49;
        DirtySample dirtySample2 = new DirtySample(items);
        dirtySample2.updateQuality();
        assertThat(items[0].quality, is(50));

        items[0].quality = 50;
        DirtySample dirtySample3 = new DirtySample(items);
        dirtySample3.updateQuality();
        assertThat(items[0].quality, is(50));

        items[0].quality = 30;
        items[0].sellIn = 1;
        DirtySample dirtySample4 = new DirtySample(items);
        dirtySample4.updateQuality();
        assertThat(items[0].quality, is(31));
    }

    @Test
    public void 이름이_1번째인경우_퀄리티가50미만이면1증가하고_sellin11미만에퀄리티가여전히50미만이면1증가하고_sellin6미만에여전히50미만이면1증가__이후sellin이0미만이되면_퀄리티0으로(){
        items[1].quality = 30;
        DirtySample dirtySample1 = new DirtySample(items);
        dirtySample1.updateQuality();
        assertThat(items[1].quality, is(0));

        items[1].quality = 30;
        items[1].sellIn = 11;
        DirtySample dirtySample2 = new DirtySample(items);
        dirtySample2.updateQuality();
        assertThat(items[1].quality, is(31));

        items[1].quality = 30;
        items[1].sellIn = 6;
        DirtySample dirtySample3 = new DirtySample(items);
        dirtySample3.updateQuality();
        assertThat(items[1].quality, is(32));

        items[1].quality = 30;
        items[1].sellIn = 5;
        DirtySample dirtySample4 = new DirtySample(items);
        dirtySample4.updateQuality();
        assertThat(items[1].quality, is(33));
    }

    @Test
    public void 이름이_2번째인경우_퀄리티언제든그대로(){
        items[2].quality = 50;
        DirtySample dirtySample1 = new DirtySample(items);
        dirtySample1.updateQuality();
        assertThat(items[2].quality, is(50));

        items[2].quality = 30;
        DirtySample dirtySample2 = new DirtySample(items);
        dirtySample2.updateQuality();
        assertThat(items[2].quality, is(30));

        items[2].quality = 0;
        DirtySample dirtySample3 = new DirtySample(items);
        dirtySample3.updateQuality();
        assertThat(items[2].quality, is(0));

        items[2].quality = -30;
        DirtySample dirtySample4 = new DirtySample(items);
        dirtySample4.updateQuality();
        assertThat(items[2].quality, is(-30));
    }

    @Test
    public void 이름이_3번째인경우_퀄리티가0보다크면1감소__이후sellin이0미만이되면_퀄리티가여전히0보다크면1감소(){
        items[3].quality = 30;
        DirtySample dirtySample1 = new DirtySample(items);
        dirtySample1.updateQuality();
        assertThat(items[3].quality, is(28));

        items[3].quality = 1;
        DirtySample dirtySample2 = new DirtySample(items);
        dirtySample2.updateQuality();
        assertThat(items[3].quality, is(0));

        items[3].quality = 0;
        DirtySample dirtySample3 = new DirtySample(items);
        dirtySample3.updateQuality();
        assertThat(items[3].quality, is(0));

        items[3].quality = 30;
        items[3].sellIn = 1;
        DirtySample dirtySample4 = new DirtySample(items);
        dirtySample4.updateQuality();
        assertThat(items[3].quality, is(29));
    }
}
