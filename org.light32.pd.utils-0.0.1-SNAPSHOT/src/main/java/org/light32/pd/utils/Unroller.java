package org.light32.pd.utils;

import java.util.List;
import java.util.function.Consumer;

/**
 * provides unrolled loops.  An unrolled loop is a loop that processes multiple entries of an array or list at once.
 *
 * Since the point of this technique is efficiency by use of a loop block that avoids dynamic code in each iteration,
 * this requires some duplication.
 * <p>
 * Each xN method will process the List N times per iteration by calling the supplied Consumer.
 * bounds checking will be done on each call to ensure that a List with a size not divisible by N can be processed
 * without error.
 *
 * @author jwhitt 9/14/16
 */
public class Unroller {

    public static <R> void x2(List<R> list, final Consumer<R> action) {
        for (int i = 0; i < list.size(); i += 2) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
        }
    }

    public static <R> void x3(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 3) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
        }
    }

    public static <R> void x4(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 4) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
        }
    }

    public static <R> void x5(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 5) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
        }
    }

    public static <R> void x6(List<R> list, final Consumer<R> action) {
        for (int i = 0; i < list.size(); i += 6) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
        }
    }

    public static <R> void x7(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 7) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
            if ((i + 6) < list.size()) action.accept(list.get(i + 6));
        }
    }

    public static <R> void x8(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 8) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
            if ((i + 6) < list.size()) action.accept(list.get(i + 6));
            if ((i + 7) < list.size()) action.accept(list.get(i + 7));
        }
    }

    public static <R> void x9(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 9) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
            if ((i + 6) < list.size()) action.accept(list.get(i + 6));
            if ((i + 7) < list.size()) action.accept(list.get(i + 7));
            if ((i + 8) < list.size()) action.accept(list.get(i + 8));
        }
    }

    public static <R> void x10(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 10) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
            if ((i + 6) < list.size()) action.accept(list.get(i + 6));
            if ((i + 7) < list.size()) action.accept(list.get(i + 7));
            if ((i + 8) < list.size()) action.accept(list.get(i + 8));
            if ((i + 9) < list.size()) action.accept(list.get(i + 9));
        }
    }


    public static <R> void x11(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 11) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
            if ((i + 6) < list.size()) action.accept(list.get(i + 6));
            if ((i + 7) < list.size()) action.accept(list.get(i + 7));
            if ((i + 8) < list.size()) action.accept(list.get(i + 8));
            if ((i + 9) < list.size()) action.accept(list.get(i + 9));
            if ((i + 10) < list.size()) action.accept(list.get(i + 10));
        }
    }

    public static <R> void x12(List<R> list, final Consumer<R> action) {

        for (int i = 0; i < list.size(); i += 12) {
            action.accept(list.get(i));
            if ((i + 1) < list.size()) action.accept(list.get(i + 1));
            if ((i + 2) < list.size()) action.accept(list.get(i + 2));
            if ((i + 3) < list.size()) action.accept(list.get(i + 3));
            if ((i + 4) < list.size()) action.accept(list.get(i + 4));
            if ((i + 5) < list.size()) action.accept(list.get(i + 5));
            if ((i + 6) < list.size()) action.accept(list.get(i + 6));
            if ((i + 7) < list.size()) action.accept(list.get(i + 7));
            if ((i + 8) < list.size()) action.accept(list.get(i + 8));
            if ((i + 9) < list.size()) action.accept(list.get(i + 9));
            if ((i + 10) < list.size()) action.accept(list.get(i + 10));
            if ((i + 11) < list.size()) action.accept(list.get(i + 11));
        }
    }

    /**
     * finds the largest number that the parameter is divisible by
     *
     * @param f
     * @return
     */
    public static int gfac(int f) {
        int x = f - 1;

        while ((f % x) != 0) {
            x--;
        }
        return x;
    }

}
