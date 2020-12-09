package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day9 extends AOCPuzzle
{

	public Day9()
	{
		super("9");
	}

	@Override
	public void solve(List<String> input)
	{
		int badNum = 0;
		int badNumIndex = 0;

		List<Integer> prev25 = new ArrayList<>();
		for(int index = 0; index < 25; index++)
		{
			prev25.add(Integer.parseInt(input.get(index)));
		}

		for(int i = 25; i < input.size(); i++)
		{
			int num = Integer.parseInt(input.get(i));
			boolean found = false;
			for(int j = 0; j < prev25.size(); j++)
			{
				if(found)
					break;
				for(int k = j + 1; k < prev25.size(); k++)
				{
					int prev1 = prev25.get(j);
					int prev2 = prev25.get(k);
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
				System.out.print("Part 1: " + badNum + ", ");
				lap();
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
				int sum = 0;
				int smallest = Integer.MAX_VALUE;
				int largest = Integer.MIN_VALUE;
				for(int k = i; k <= j; k++)
				{
					int num = Integer.parseInt(input.get(k));
					sum += num;
					if(num < smallest)
						smallest = num;
					else if(num > largest)
						largest = num;
				}

				if(sum == badNum)
				{
					found = true;
					System.out.print("Part 2: " + (smallest + largest) + ", ");
					break;
				}
			}
		}
	}
}
