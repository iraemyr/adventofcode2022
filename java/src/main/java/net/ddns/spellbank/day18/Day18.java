package net.ddns.spellbank.day18;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day18 {
    record Point3D(int x, int y, int z) {
    }

    public static void main(String[] args) {
        String file = "day18/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 4322
        System.out.println(part2(lines)); // 2516
    }

    public static long part1(String[] lines) {
        var droplets = new HashSet<Point3D>();
        for (var line : lines) {
            var fields = line.split(",");
            droplets.add(
                    new Point3D(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
        }
        return countFaces(droplets);
    }

    public static long part2(String[] lines) {
        var droplets = new HashSet<Point3D>();
        for (var line : lines) {
            var fields = line.split(",");
            droplets.add(
                    new Point3D(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
        }
        return steam(droplets);
    }

    private static long countFaces(Set<Point3D> droplets) {
        long result = 0;
        for (var droplet : droplets) {
            if (!droplets.contains(new Point3D(droplet.x + 1, droplet.y, droplet.z)))
                result++;
            if (!droplets.contains(new Point3D(droplet.x - 1, droplet.y, droplet.z)))
                result++;
            if (!droplets.contains(new Point3D(droplet.x, droplet.y + 1, droplet.z)))
                result++;
            if (!droplets.contains(new Point3D(droplet.x, droplet.y - 1, droplet.z)))
                result++;
            if (!droplets.contains(new Point3D(droplet.x, droplet.y, droplet.z + 1)))
                result++;
            if (!droplets.contains(new Point3D(droplet.x, droplet.y, droplet.z - 1)))
                result++;
        }
        return result;
    }

    private static long steam(Set<Point3D> droplets) {
        var map = new HashMap<Point3D, Character>();
        for (var p : droplets)
            map.put(p, '#');

        var steam = new HashSet<Point3D>();
        var steam2 = new HashSet<Point3D>();
        steam.add(new Point3D(0, 0, 0));
        while (!steam.isEmpty()) {
            for (var droplet : steam) {
                Point3D p = new Point3D(droplet.x + 1, droplet.y, droplet.z);
                if (boundsCheck(p) && map.getOrDefault(p, '.') == '.')
                    steam2.add(p);
                p = new Point3D(droplet.x - 1, droplet.y, droplet.z);
                if (boundsCheck(p) && map.getOrDefault(p, '.') == '.')
                    steam2.add(p);
                p = new Point3D(droplet.x, droplet.y + 1, droplet.z);
                if (boundsCheck(p) && map.getOrDefault(p, '.') == '.')
                    steam2.add(p);
                p = new Point3D(droplet.x, droplet.y - 1, droplet.z);
                if (boundsCheck(p) && map.getOrDefault(p, '.') == '.')
                    steam2.add(p);
                p = new Point3D(droplet.x, droplet.y, droplet.z + 1);
                if (boundsCheck(p) && map.getOrDefault(p, '.') == '.')
                    steam2.add(p);
                p = new Point3D(droplet.x, droplet.y, droplet.z - 1);
                if (boundsCheck(p) && map.getOrDefault(p, '.') == '.')
                    steam2.add(p);
            }
            for (var p : steam)
                map.put(p, 's');
            var tmp = steam;
            steam = steam2;
            steam2 = tmp;
            tmp.clear();
        }
        // printSteamSlice(map);
        return countSurfaceFaces(map);
    }

    private static boolean boundsCheck(Point3D p) {
        return p.x >= -1 && p.x < 25 && p.y >= -1 && p.y < 25 && p.z >= -1 && p.z < 25;
    }

    private static long countSurfaceFaces(HashMap<Point3D, Character> droplets) {
        long result = 0;
        for (var droplet : droplets.keySet()) {
            if (droplets.get(droplet) != '#')
                continue;
            if (droplets.getOrDefault(new Point3D(droplet.x + 1, droplet.y, droplet.z), '.') == 's')
                result++;
            if (droplets.getOrDefault(new Point3D(droplet.x - 1, droplet.y, droplet.z), '.') == 's')
                result++;
            if (droplets.getOrDefault(new Point3D(droplet.x, droplet.y + 1, droplet.z), '.') == 's')
                result++;
            if (droplets.getOrDefault(new Point3D(droplet.x, droplet.y - 1, droplet.z), '.') == 's')
                result++;
            if (droplets.getOrDefault(new Point3D(droplet.x, droplet.y, droplet.z + 1), '.') == 's')
                result++;
            if (droplets.getOrDefault(new Point3D(droplet.x, droplet.y, droplet.z - 1), '.') == 's')
                result++;
        }
        return result;
    }

    @SuppressWarnings("unused")
    private static void printSlice(Set<Point3D> droplets) {
        for (int z = -1; z < 23; z++) {
            for (int i = -1; i < 23; i++) {
                for (int j = -1; j < 23; j++) {
                    var p = new Point3D(i, j, z);
                    System.out.print(droplets.contains(p) ? '#' : '.');
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    @SuppressWarnings("unused")
    private static void printSteamSlice(HashMap<Point3D, Character> droplets) {
        for (int z = -1; z < 23; z++) {
            for (int i = -1; i < 23; i++) {
                for (int j = -1; j < 23; j++) {
                    var p = new Point3D(i, j, z);
                    System.out.print(droplets.getOrDefault(p, '.'));
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}