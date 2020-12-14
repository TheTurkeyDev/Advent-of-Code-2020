package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day05 extends AOCPuzzle
{
	public Day05()
	{
		super("5");
	}

	@Override
	void solve(List<String> input)
	{
		int max = 0;
		List<Integer> ids = new ArrayList<>();
		for(String s : input)
		{
			int row = 0;
			int col = 0;
			for(int i = 0; i < 7; i++)
			{
				if(s.charAt(i) == 'B')
					row += Math.pow(2, 6 - i);
			}

			for(int i = 7; i < 10; i++)
			{
				if(s.charAt(i) == 'R')
					col += Math.pow(2, 9 - i);
			}

			int rowCol = (row * 8) + col;
			ids.add(rowCol);
			if(rowCol > max)
				max = rowCol;
		}

		lap(max);

		int lastID = -1;
		ids.sort(Integer::compareTo);
		for(int i : ids)
		{
			if(lastID != -1 && i - lastID == 2)
			{
				lap(i - 1);
				break;
			}
			lastID = i;
		}
	}
}
