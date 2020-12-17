package dev.theturkey.aoc2020;

import java.util.List;

public class Day03 extends AOCPuzzle
{
	public Day03()
	{
		super("3");
	}

	@Override
	void solve(List<String> input)
	{
		char[][] map = new char[323][31];

		for(int i = 0; i < 323; i++)
		{
			String line = input.get(i);
			char[] chars = line.toCharArray();
			System.arraycopy(chars, 0, map[i], 0, chars.length);
		}

		long trees = getTreesHit(3, 1, map);
		lap(trees);
		trees *= getTreesHit(1, 1, map);
		trees *= getTreesHit(5, 1, map);
		trees *= getTreesHit(7, 1, map);
		trees *= getTreesHit(1, 2, map);

		lap(trees);
	}

	public int getTreesHit(int slopeX, int slopeY, char[][] map)
	{
		int x = 0;
		int y = 0;

		int trees = 0;

		while(y < 323)
		{
			char chatAt = map[y][x];
			if(chatAt == '#')
			{
				trees++;
			}

			x = (x + slopeX) % 31;
			y += slopeY;
		}

		return trees;
	}
}
