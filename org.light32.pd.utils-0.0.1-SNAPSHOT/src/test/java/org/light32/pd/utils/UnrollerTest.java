package org.light32.pd.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unroller unit test
 *
 * @author jwhitt 9/14/16
 */
public class UnrollerTest {


    @Test
    public void testGfac() {
        assertEquals(5, Unroller.gfac(10));
        assertEquals(12, Unroller.gfac(24));
        assertEquals(50, Unroller.gfac(100));
        assertEquals(512, Unroller.gfac(1024));

        // odd
        assertEquals(11, Unroller.gfac(77));
        assertEquals(111, Unroller.gfac(333));

    }

    @Test
    public void testX2() {
        List<String> input = getStringList(20);
        List<String> outList = new ArrayList<>();

        Unroller.x2(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX2_uneven() {
        List<String> input = getStringList(15);
        List<String> outList = new ArrayList<>();

        Unroller.x2(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }


    @Test
    public void testX3() {
        List<String> input = getStringList(45);
        List<String> outList = new ArrayList<>();

        Unroller.x3(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX3_uneven() {
        List<String> input = getStringList(49);
        List<String> outList = new ArrayList<>();

        Unroller.x3(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX4() {
        List<String> input = getStringList(48);
        List<String> outList = new ArrayList<>();

        Unroller.x4(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX4_uneven() {
        List<String> input = getStringList(33);
        List<String> outList = new ArrayList<>();

        Unroller.x4(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX5() {
        List<String> input = getStringList(55);
        List<String> outList = new ArrayList<>();

        Unroller.x5(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX5_uneven() {
        List<String> input = getStringList(56);
        List<String> outList = new ArrayList<>();

        Unroller.x5(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX6() {
        List<String> input = getStringList(72);
        List<String> outList = new ArrayList<>();

        Unroller.x6(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX6_uneven() {
        List<String> input = getStringList(50);
        List<String> outList = new ArrayList<>();

        Unroller.x6(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX7() {
        List<String> input = getStringList(49);
        List<String> outList = new ArrayList<>();

        Unroller.x7(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX7_uneven() {
        List<String> input = getStringList(40);
        List<String> outList = new ArrayList<>();

        Unroller.x7(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX8() {
        List<String> input = getStringList(64);
        List<String> outList = new ArrayList<>();

        Unroller.x8(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX8_uneven() {
        List<String> input = getStringList(65);
        List<String> outList = new ArrayList<>();

        Unroller.x8(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX9() {
        List<String> input = getStringList(81);
        List<String> outList = new ArrayList<>();

        Unroller.x9(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX9_uneven() {
        List<String> input = getStringList(81);
        List<String> outList = new ArrayList<>();

        Unroller.x9(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX10() {
        List<String> input = getStringList(100);
        List<String> outList = new ArrayList<>();

        Unroller.x10(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX10_uneven() {
        List<String> input = getStringList(99);
        List<String> outList = new ArrayList<>();

        Unroller.x10(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX11() {
        List<String> input = getStringList(121);
        List<String> outList = new ArrayList<>();

        Unroller.x11(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX11_uneven() {
        List<String> input = getStringList(104);
        List<String> outList = new ArrayList<>();

        Unroller.x11(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX12() {
        List<String> input = getStringList(144);
        List<String> outList = new ArrayList<>();

        Unroller.x12(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testX12_uneven() {
        List<String> input = getStringList(145);
        List<String> outList = new ArrayList<>();

        Unroller.x12(input, (s) -> {
            outList.add(s + "processed");
        });
        assertTheList(outList);
    }

    @Test
    public void testSpeed() {

        // 12^5
        List<String> input = getStringList(248832);
        List<String> outList = new ArrayList<>();

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < input.size(); i++) {
            outList.add(input.get(i) + "processed");
        }
        long stop4 = System.currentTimeMillis();
        System.out.println("C style for() elapsed = " + (stop4 - start4));

        outList.clear();

        long starte = System.currentTimeMillis();
        for (String s : input) {
            outList.add(s + "processed");
        }
        long stope = System.currentTimeMillis();
        System.out.println("iterator for() elapsed = " + (stope - starte));

        outList.clear();

        long start1 = System.currentTimeMillis();
        input.stream().forEach((s) -> {
            outList.add(s + "processed");
        });
        long stop1 = System.currentTimeMillis();
        System.out.println("stream elapsed = " + (stop1 - start1));


        outList.clear();
        long start = System.currentTimeMillis();
        Unroller.x12(input, (s) -> {
            outList.add(s + "processed");
        });
        long stop = System.currentTimeMillis();
        System.out.println("x12 elapsed = " + (stop - start));
    }

    ////
    // helpers
    ////
    private void assertTheList(List<String> input) {
        for (int x = 0; x < input.size(); x++) {
            assertEquals("testString" + x + "processed", input.get(x));
        }
    }

    private List<String> getStringList(int i) {
        List<String> out = new ArrayList<>();
        for (int x = 0; x <= i; x++) {
            out.add("testString" + x);
        }
        return out;
    }
}
