package net.ddns.spellbank.day09;

public interface IRope {
    public void up(int d);

    public void down(int d);

    public void left(int d);

    public void right(int d);

    public long numVisited();

    default public void doMoves(String[] lines) {
        for (var line : lines) {
            var fields = line.split(" ");
            var dist = Integer.parseInt(fields[1]);
            switch (fields[0]) {
                case "U":
                    up(dist);
                    break;
                case "D":
                    down(dist);
                    break;
                case "L":
                    left(dist);
                    break;
                case "R":
                    right(dist);
                    break;
                default:
                    System.out.println("Invalid direction");
            }
        }
    }
}
