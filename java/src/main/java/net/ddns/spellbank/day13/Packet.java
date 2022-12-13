package net.ddns.spellbank.day13;

import java.util.ArrayList;

public class Packet implements Comparable<Packet> {
    record IndexAndList(int index, Either list) {
    };

    private Either vals;

    public Packet(String s) {
        vals = parseList(s, 1).list;
    }

    private IndexAndList parseList(String s, int index) {
        var li = new Either(new ArrayList<Either>());
        while (s.charAt(index) != ']') {
            if (s.charAt(index) == '[') {
                var rec = parseList(s, ++index);
                index = rec.index;
                li.add(rec.list);
            } else if (Character.isDigit(s.charAt(index))) {
                var endIndex = index + 1;
                while (Character.isDigit(s.charAt(endIndex))) {
                    endIndex++;
                }
                li.add(new Either(Integer.parseInt(s.substring(index, endIndex))));
                index = endIndex;
            } else
                index++; // ignore comma
        }
        return new IndexAndList(index + 1, li);
    }

    public Either vals() {
        return vals;
    }

    public String toString() {
        return vals.toString();
    }

    @Override
    public int compareTo(Packet o) {
        return this.vals.compareTo(o.vals);
    }

}
