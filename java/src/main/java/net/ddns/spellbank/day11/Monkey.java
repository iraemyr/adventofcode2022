package net.ddns.spellbank.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    static List<Monkey> monkeys;
    List<Long> items;
    boolean mul;
    int opVal;
    int div;
    int trueDest;
    int falseDest;
    long inspected;
    static long lcm;

    public Monkey() {
        items = new ArrayList<Long>();
        inspected = 0;
    }

    public void setMul(boolean mul) {
        this.mul = mul;
    }

    public void setOpVal(int opVal) {
        this.opVal = opVal;
    }

    public void setDiv(int div) {
        this.div = div;
    }

    public void setTrueDest(int dest) {
        this.trueDest = dest;
    }

    public void setFalseDest(int dest) {
        this.falseDest = dest;
    }

    public void addItem(long item) {
        items.add(item);
    }

    public void doOp() {
        for (var item : items) {
            inspected++;
            var val = opVal == -1 ? item : opVal;
            var worry = mul ? item * val : item + val;
            worry /= 3;
            if (worry % div == 0)
                monkeys.get(trueDest).addItem(worry);
            else
                monkeys.get(falseDest).addItem(worry);
        }
        items.clear();
    }

    public void doOpNoReduction() {
        for (var item : items) {
            inspected++;
            var val = opVal == -1 ? item : opVal;
            var worry = mul ? item * val : item + val;
            worry %= lcm;
            if (worry % div == 0)
                monkeys.get(trueDest).addItem(worry);
            else
                monkeys.get(falseDest).addItem(worry);
        }
        items.clear();
    }

    public long getInspected() {
        return inspected;
    }

    static void setMonkeys(List<Monkey> li) {
        monkeys = li;
        long acc = 1;
        for (var monkey : monkeys) {
            acc = lcm(acc, monkey.div);
        }
        lcm = acc;
    }

    static long gcd(long a, long b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
