package dev.theturkey.aoc2020;

import java.util.List;

public class Day1 extends AOCPuzzle
{
	public Day1()
	{
		super("1");
	}

	@Override
	void solve(List<String> inputStrs)
	{
		List<Integer> inputs = convertToInts(inputStrs);
		boolean complete = false;
		for(int i = 0; i < inputs.size(); i++)
		{
			if(complete)
				break;
			for(int j = i + 1; j < inputs.size(); j++)
			{
				int int1 = inputs.get(i);
				int int2 = inputs.get(j);
				if(int1 + int2 == 2020)
				{
					lap(int1 * int2);
					complete = true;
					break;
				}
			}
		}

		for(int i = 0; i < inputs.size(); i++)
		{
			for(int j = i + 1; j < inputs.size(); j++)
			{
				for(int k = j + 1; k < inputs.size(); k++)
				{
					int int1 = inputs.get(i);
					int int2 = inputs.get(j);
					int int3 = inputs.get(k);
					if(int1 + int2 + int3 == 2020)
					{
						lap(int1 * int2 * int3);
						return;
					}
				}
			}
		}
	}
}
