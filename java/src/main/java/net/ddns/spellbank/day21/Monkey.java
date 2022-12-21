package net.ddns.spellbank.day21;

public class Monkey {

    public String name;
    private Long number;
    private Character op;
    private Monkey monkey1;
    private Monkey monkey2;

    public Monkey(String s) {
        name = s;
        number = null;
        op = null;
        monkey1 = null;
        monkey2 = null;
    }

    public Monkey(long num) {
        number = num;
    }

    public void setNumber(long num) {
        number = num;
    }

    public void setOp(char c) {
        op = c;
    }

    public void setMonkey1(Monkey m) {
        monkey1 = m;
    }

    public void setMonkey2(Monkey m) {
        monkey2 = m;
    }

    public long getNumber() {
        if (number != null)
            return number;
        switch (op) {
            case '+':
                return monkey1.getNumber() + monkey2.getNumber();
            case '-':
                return monkey1.getNumber() - monkey2.getNumber();
            case '*':
                return monkey1.getNumber() * monkey2.getNumber();
            case '/':
                return monkey1.getNumber() / monkey2.getNumber();
            case '=':
                return monkey1.getNumber() - monkey2.getNumber();
        }
        System.out.println("Invalid op");
        return 0;
    }

    public String toString() {
        if (number != null)
            return name + ", " + number;
        return name + ", " + monkey1.name + " " + op + " " + monkey2.name;
    }

    public void reset() {
        if (monkey1 != null)
            number = null;
    }

}
