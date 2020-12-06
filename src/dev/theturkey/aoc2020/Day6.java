package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day6 extends AOCPuzzle
{
	public Day6()
	{
		super("6");
	}

	@Override
	void solve(List<String> input)
	{
		int total = 0;
		List<Character> yesTo = new ArrayList<>();
		boolean first = true;
		for(String s : input)
		{
			if(s.trim().isEmpty())
			{
				total += yesTo.size();
				yesTo.clear();
				first = true;
			}
			else
			{
				if(first)
				{
					for(char c : s.trim().toCharArray())
						yesTo.add(c);
				}
				else
				{
					for(int i = yesTo.size() - 1; i >= 0; i--)
					{
						char c = yesTo.get(i);
						boolean contains = false;
						for(char c2 : s.trim().toCharArray())
						{
							if(c2 == c)
							{
								contains = true;
								break;
							}
						}

						if(!contains)
							yesTo.remove(i);
					}
				}

				first = false;
			}
		}

		total += yesTo.size();

		System.out.println("Part 2: " + total);
	}
}
