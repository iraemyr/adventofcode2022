package net.ddns.spellbank.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day19 {
    record Blueprint(int id, int oreBotCost, int clayBotCost, int obsidianBotOreCost, int obsidianBotClayCost,
            int geodeBotOreCost, int geodeBotObsidianCost) {
    }

    private static int MAX;

    record State(Blueprint bp, int minutes, int orebots, int claybots, int obsidianbots, int geodebots, int ore,
            int clay, int obsidian, int geode) {
        public static State start(Blueprint bp) {
            return new State(bp, 24, 1, 0, 0, 0, 0, 0, 0, 0);
        }

        public List<State> getStates() {
            var states = new ArrayList<State>();
            if (ore >= bp.geodeBotOreCost && obsidian >= bp.geodeBotObsidianCost) {
                states.add(new State(bp, minutes - 1, orebots, claybots, obsidianbots, geodebots + 1,
                        ore - bp.geodeBotOreCost + orebots, clay + claybots,
                        obsidian - bp.geodeBotObsidianCost + obsidianbots, geode + geodebots));
                return states;
            }

            if (ore >= bp.obsidianBotOreCost && clay >= bp.obsidianBotClayCost)
                states.add(new State(bp, minutes - 1, orebots, claybots, obsidianbots + 1, geodebots,
                        ore - bp.obsidianBotOreCost + orebots, clay - bp.obsidianBotClayCost + claybots,
                        obsidian + obsidianbots, geode + geodebots));

            if (ore >= bp.clayBotCost)
                states.add(new State(bp, minutes - 1, orebots, claybots + 1, obsidianbots, geodebots,
                        ore - bp.clayBotCost + orebots, clay + claybots, obsidian + obsidianbots, geode + geodebots));

            if (ore >= bp.oreBotCost)
                states.add(new State(bp, minutes - 1, orebots + 1, claybots, obsidianbots, geodebots,
                        ore - bp.oreBotCost + orebots, clay + claybots, obsidian + obsidianbots, geode + geodebots));
            states.add(new State(bp, minutes - 1, orebots, claybots, obsidianbots, geodebots, ore + orebots,
                    clay + claybots, obsidian + obsidianbots, geode + geodebots));

            return states;
        }
    }

    public static void main(String[] args) {
        String file = "day19/input1";
        String[] lines = InputFile.getLines(file);
        MAX = 0;

        System.out.println(part1(lines)); // 790
        System.out.println(part2(lines)); // 7350
    }

    public static long part1(String[] lines) {
        var bps = parseInput(lines);
        long sum = 0;
        for (var bp : bps) {
            MAX = 0;
            sum += bp.id * dfs(new State(bp, 24, 1, 0, 0, 0, 0, 0, 0, 0), new HashMap<State, Integer>());
        }
        return sum;
    }

    public static long part2(String[] lines) {
        var bps = parseInput(lines);
        long result = 1;
        for (int i = 0; i < 3; i++) {
            MAX = 0;
            result *= dfs(new State(bps.get(i), 32, 1, 0, 0, 0, 0, 0, 0, 0), new HashMap<State, Integer>());
        }
        return result;
    }

    private static List<Blueprint> parseInput(String[] lines) {
        var result = new ArrayList<Blueprint>();
        for (int i = 0; i < lines.length; i++) {
            int id = i + 1;
            var fields = lines[i].split(" ");
            result.add(new Blueprint(id, Integer.parseInt(fields[6]), Integer.parseInt(fields[12]),
                    Integer.parseInt(fields[18]), Integer.parseInt(fields[21]), Integer.parseInt(fields[27]),
                    Integer.parseInt(fields[30])));
        }
        return result;
    }

    private static int dfs(State state, HashMap<State, Integer> cache) {
        if ((state.minutes * state.minutes - state.minutes) / 2 + state.geodebots * state.minutes <= MAX - state.geode)
            return -1;

        var r = cache.get(state);
        if (r != null)
            return r;
        if (state.minutes == 0) {
            cache.put(state, state.geode);
            return state.geode;
        }
        for (var s : state.getStates())
            MAX = Math.max(MAX, dfs(s, cache));
        cache.put(state, state.geode);
        return MAX;
    }

}