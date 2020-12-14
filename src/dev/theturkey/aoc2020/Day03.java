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
			for(int j = 0; j < chars.length; j++)
			{
				map[i][j] = chars[j];
			}
		}

		long trees = getTreesHit(1, 1, map);
		trees *= getTreesHit(3, 1, map);
		trees *= getTreesHit(5, 1, map);
		trees *= getTreesHit(7, 1, map);
		trees *= getTreesHit(1, 2, map);

		System.out.println("Trees hit: " + trees);
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
