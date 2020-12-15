package dev.theturkey.aoc2020;

import java.util.ArrayList;
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
		List<Integer> spoken = new ArrayList<>();
		Map<Integer, Integer> seenLast = new HashMap<>();
		int last = -1;
		for(int i = 0; i < numStrings.length; i++)
		{
			int num = Integer.parseInt(numStrings[i]);
			spoken.add(num);
			if(i == numStrings.length - 1)
			{
				last = num;
			}
			else
			{
				seenLast.put(num, spoken.size());
			}
		}

		while(spoken.size() < 2020)
		{
			last = step(last, spoken, seenLast);
		}

		lap(last);

		while(spoken.size() < 30000000)
		{
			last = step(last, spoken, seenLast);
		}

		lap(last);
	}

	public int step(int lastSpoken, List<Integer> spoken, Map<Integer, Integer> seenLast)
	{
		int seenLastTemp = seenLast.getOrDefault(lastSpoken, -1);
		seenLast.put(lastSpoken, spoken.size());
		spoken.add(lastSpoken);
		return seenLastTemp == -1 ? 0 : ((spoken.size() - 1) - seenLastTemp);
	}
}
