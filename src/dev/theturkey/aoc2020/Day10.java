package dev.theturkey.aoc2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 extends AOCPuzzle
{
	private static Map<String, Long> cache;

	public Day10()
	{
		super("10");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		List<Integer> inputs = convertToInts(inputStrs);
		inputs.sort(Integer::compareTo);
		inputs.add(0, 0);
		inputs.add(inputs.get(inputs.size() - 1) + 3);

		int diff1 = 0;
		int diff3 = 0;

		for(int i = 1; i < inputs.size(); i++)
		{
			int diff = inputs.get(i) - inputs.get(i - 1);
			if(diff == 1)
			{
				diff1++;
			}
			else if(diff == 3)
			{
				diff3++;
			}
		}
		lap(diff1 * diff3);

		cache = new HashMap<>();
		long arrangements = getArrangements(inputs);
		lap(arrangements);

	}


	public long getArrangements(List<Integer> inputs)
	{
		if(inputs.size() == 1)
			return 1;

		long arrangements = 0;
		int index = 1;
		int current = inputs.get(0);
		while(inputs.size() > index && inputs.get(index) - current < 4)
		{
			List<Integer> subList = inputs.subList(index, inputs.size());
			String subListStr = Arrays.toString(subList.toArray(new Integer[0]));

			if(cache.containsKey(subListStr))
			{
				arrangements += cache.get(subListStr);
			}
			else
			{
				long subArrangements = getArrangements(subList);
				cache.put(subListStr, subArrangements);
				arrangements += subArrangements;
			}
			index++;
		}
		return arrangements;
	}
}
