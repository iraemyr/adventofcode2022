package net.ddns.spellbank.day16;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day16 {

    record Valve(int id, int flow, List<Edge> tunnels) {
    };

    record Edge(String name, int cost) {
    };

    record State(int time, String location, int open) {
    };

    record DP(int minute, String location, Set<String> open) {
    };

    public static void main(String[] args) {
        String file = "day16/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1741
        System.out.println(part2(lines)); // 2316
    }

    public static long part1(String[] lines) {
        var map = parseInput(lines);
        return dfs(new State(30, "AA", 0), map, new HashMap<State, Integer>());
    }

    public static long part2(String[] lines) {
        var map = parseInput(lines);
        int max = 0;
        int b = (1 << map.size()) - 1;
        for (int i = 0; i < (b + 1) / 2; i++) {
            max = Math.max(max, dfs(new State(26, "AA", i), map, new HashMap<State, Integer>())
                    + dfs(new State(26, "AA", b ^ i), map, new HashMap<State, Integer>()));
        }
        return max;
    }

    private static Map<String, Valve> parseInput(String[] lines) {
        var map = new HashMap<String, Valve>();
        var flowMap = new HashMap<String, Integer>();
        var tunnelMap = new HashMap<String, List<String>>();
        int id = 1;
        for (var line : lines) {
            var fields = line.split("; ");
            var valveData = fields[0];
            var data = valveData.split(" ");
            var name = data[1];
            var flow = Integer.parseInt(data[4].split("=")[1]);
            flowMap.put(name, flow);
            List<String> tunnels = new ArrayList<String>();
            var tData = fields[1].split("valve");
            if (!tData[1].startsWith("s"))
                tunnels.add(tData[1].substring(1));
            else {
                var tuns = tData[1].substring(2).split(", ");
                for (var t : tuns)
                    tunnels.add(t);
            }
            tunnelMap.put(name, tunnels);
        }

        // Reduce to nodes of interest
        var visited = new HashSet<String>();
        var q = new ArrayDeque<Edge>();
        for (var valve : flowMap.keySet()) {
            visited.clear();
            q.clear();
            var distances = new ArrayList<Edge>();
            if (!valve.equals("AA") && flowMap.get(valve) == 0)
                continue;
            visited.add(valve);
            q.add(new Edge(valve, 0));

            while (!q.isEmpty()) {
                var edge = q.removeFirst();
                for (var neighbor : tunnelMap.get(edge.name)) {
                    if (visited.contains(neighbor))
                        continue;
                    visited.add(neighbor);
                    var n = new Edge(neighbor, edge.cost + 1);
                    if (flowMap.get(n.name) != 0)
                        distances.add(n);
                    q.add(n);
                }
            }
            if (valve.equals("AA"))
                map.put(valve, new Valve(0, flowMap.get(valve), distances));
            else
                map.put(valve, new Valve(id++, flowMap.get(valve), distances));
        }
        return map;
    }

    private static int dfs(State state, Map<String, Valve> map, Map<State, Integer> cache) {
        if (cache.containsKey(state))
            return cache.get(state);
        int max = 0;
        var v = map.get(state.location);
        for (var neighbor : v.tunnels) {
            var n = map.get(neighbor.name);
            var timeRemaining = state.time - (neighbor.cost + 1);
            if (timeRemaining <= 0)
                continue;
            int mask = 1 << n.id;
            if ((mask & state.open) != 0)
                continue;
            max = Math.max(max, dfs(new State(timeRemaining, neighbor.name, mask | state.open), map, cache)
                    + timeRemaining * n.flow);
        }
        cache.put(state, max);
        return max;
    }

}