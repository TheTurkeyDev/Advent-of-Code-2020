package dev.theturkey.aoc2020;

import java.util.List;

public class Day2 extends AOCPuzzle
{
	public Day2()
	{
		super("2");
	}

	@Override
	void solve(List<String> input)
	{
		/* PART 1*/
		int correct = 0;
		for(String s : input)
		{
			String[] parts = s.split(" ");
			String[] minMax = parts[0].split("-");
			int min = Integer.parseInt(minMax[0]);
			int max = Integer.parseInt(minMax[1]);
			char letter = parts[1].charAt(0);

			int occurrences = 0;
			for(char c : parts[2].toCharArray())
			{
				if(c == letter)
				{
					occurrences++;
				}
			}

			if(occurrences >= min && occurrences <= max)
			{
				correct++;
			}
		}

		lap(correct);

		/* PART 2*/
		correct = 0;
		for(String s : input)
		{
			String[] parts = s.split(" ");
			String[] minMax = parts[0].split("-");
			int pos1 = Integer.parseInt(minMax[0]) - 1;
			int pos2 = Integer.parseInt(minMax[1]) - 1;
			char letter = parts[1].charAt(0);

			if(parts[2].charAt(pos1) == letter ^ parts[2].charAt(pos2) == letter)
			{
				correct++;
			}
		}

		lap(correct);
	}
}
