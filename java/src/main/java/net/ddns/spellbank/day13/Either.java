package net.ddns.spellbank.day13;

import java.util.ArrayList;
import java.util.List;

public class Either implements Comparable<Either> {
    private int num;
    private List<Either> list = null;

    public Either(int x) {
        num = x;
    }

    public Either(List<Either> li) {
        list = li;
    }

    public boolean isList() {
        return list != null;
    }

    public Integer getNum() {
        return !isList() ? num : null;
    }

    public List<Either> getList() {
        return isList() ? list : null;
    }

    public boolean add(Either val) {
        if (!isList())
            return false;
        list.add(val);
        return true;
    }

    public String toString() {
        var str = "";
        if (isList()) {
            str += "[";
            for (int i = 0; i < list.size(); i++) {
                str += list.get(i);
                if (i != list.size() - 1)
                    str += ",";
            }
            str += "]";
        } else {
            str += num;
        }
        return str;
    }

    public int size() {
        if (isList()) {
            return list.size();
        }
        return 1;
    }

    private static int checkEithers(Either left, Either right) {
        if (!left.isList() && !right.isList()) {
            if (left.getNum() > right.getNum())
                return 1;
            else if (left.getNum() == right.getNum())
                return 0;
            return -1;
        }
        List<Either> le;
        if (left.isList())
            le = left.getList();
        else {
            var tmp = new ArrayList<Either>();
            tmp.add(new Either(left.getNum()));
            le = tmp;
        }
        List<Either> ri;
        if (right.isList())
            ri = right.getList();
        else {
            var tmp = new ArrayList<Either>();
            tmp.add(new Either(right.getNum()));
            ri = tmp;
        }
        if (le.size() == 0 && ri.size() > 0)
            return -1;
        int i = 0;
        while (i < le.size()) {
            if (i >= ri.size())
                return 1;
            var e1 = le.get(i);
            var e2 = ri.get(i++);
            var result = checkEithers(e1, e2);
            if (result != 0)
                return result;
        }
        if (i == ri.size())
            return 0;
        return -1;
    }

    @Override
    public int compareTo(Either o) {
        return checkEithers(this, (Either) o);
    }
}
