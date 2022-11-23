package net.ddns.spellbank.utils;

public class MyUtils {
	@SuppressWarnings("unused")
	public static void printMap(char[][] map) {
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) System.out.print(map[row][col]);
			System.out.println();
		}
	}
}
