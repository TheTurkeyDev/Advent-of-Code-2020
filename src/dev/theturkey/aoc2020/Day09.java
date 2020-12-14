package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day09 extends AOCPuzzle
{

	public Day09()
	{
		super("9");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		List<Long> input = convertToLongs(inputStrs);
		long badNum = 0;
		int badNumIndex = 0;

		List<Long> prev25 = new ArrayList<>();
		for(int index = 0; index < 25; index++)
		{
			prev25.add(input.get(index));
		}

		for(int i = 25; i < input.size(); i++)
		{
			long num = input.get(i);
			boolean found = false;
			for(int j = 0; j < prev25.size(); j++)
			{
				if(found)
					break;
				for(int k = j + 1; k < prev25.size(); k++)
				{
					long prev1 = prev25.get(j);
					long prev2 = prev25.get(k);
					if(prev1 + prev2 == num)
					{
						found = true;
						break;
					}
				}
			}

			if(found)
			{
				prev25.remove(0);
				prev25.add(num);
			}
			else
			{
				badNum = num;
				badNumIndex = i;
				lap(badNum);
				break;
			}
		}


		boolean found = false;
		for(int i = 0; i < badNumIndex - 2; i++)
		{
			if(found)
				break;
			for(int j = i + 1; j < badNumIndex; j++)
			{
				long sum = 0;
				long smallest = Integer.MAX_VALUE;
				long largest = Integer.MIN_VALUE;
				for(int k = i; k <= j; k++)
				{
					long num = input.get(k);
					sum += num;
					if(num < smallest)
						smallest = num;
					else if(num > largest)
						largest = num;
				}

				if(sum == badNum)
				{
					found = true;
					lap(smallest + largest);
					break;
				}
			}
		}
	}
}
