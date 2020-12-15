package dev.theturkey.aoc2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 extends AOCPuzzle
{
	public Day15()
	{
		super("15");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		String[] numStrings = inputStrs.get(0).split(",");
		int index = 0;
		Map<Integer, Integer> seenLast = new HashMap<>();
		int last = -1;
		for(int i = 0; i < numStrings.length; i++)
		{
			int num = Integer.parseInt(numStrings[i]);
			index++;
			if(i == numStrings.length - 1)
			{
				last = num;
			}
			else
			{
				seenLast.put(num, index);
			}
		}

		while(index < 2020)
		{
			last = step(last, index, seenLast);
			index++;
		}

		lap(last);

		while(index < 30000000)
		{
			last = step(last, index, seenLast);
			index++;
		}

		lap(last);
	}

	public int step(int lastSpoken, int index, Map<Integer, Integer> seenLast)
	{
		int seenLastTemp = seenLast.getOrDefault(lastSpoken, -1);
		seenLast.put(lastSpoken, index);
		return seenLastTemp == -1 ? 0 : (index - seenLastTemp);
	}
}
