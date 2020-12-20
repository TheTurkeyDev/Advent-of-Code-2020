package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 extends AOCPuzzle
{
	private Map<Integer, List<List<Integer>>> rules;
	private Map<Integer, Character> rulesEnd;

	public Day19()
	{
		super("19");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		rules = new HashMap<>();
		rulesEnd = new HashMap<>();

		int stage = 0;
		int matches = 0;
		for(String s : inputStrs)
		{
			if(stage == 0)
			{
				if(s.isEmpty())
				{
					stage++;
					continue;
				}

				String[] parts = s.split(": ");
				Integer ruleID = Integer.parseInt(parts[0]);

				if(parts[1].contains("\""))
				{
					rulesEnd.put(ruleID, parts[1].replace("\"", "").trim().charAt(0));
				}
				else
				{
					List<List<Integer>> rulesList = new ArrayList<>();
					for(String expressions : parts[1].split(" \\| "))
					{
						List<Integer> list = new ArrayList<>();
						for(String expRule : expressions.trim().split(" "))
						{
							list.add(Integer.parseInt(expRule));
						}
						rulesList.add(list);
					}
					rules.put(ruleID, rulesList);
				}
			}
			else
			{
				List<Character> chars = new ArrayList<>();
				for(char c : s.trim().toCharArray())
					chars.add(c);
				if(matches(chars, 0) && chars.size() == 0)
				{
					matches++;
				}
			}
		}

		lap(matches);
	}

	public boolean matches(List<Character> chars, int rule)
	{
		if(chars.size() == 0)
			return false;

		if(rulesEnd.containsKey(rule))
		{
			if(chars.get(0) == rulesEnd.get(rule))
			{
				chars.remove(0);
				return true;
			}
		}
		else
		{
			List<List<Integer>> allSubRules = rules.get(rule);
			for(List<Integer> subRules : allSubRules)
			{
				List<Character> charsCopy = new ArrayList<>(chars);
				boolean matches = true;
				for(int i : subRules)
				{
					if(!matches(charsCopy, i))
					{
						matches = false;
						break;
					}
				}

				if(matches)
				{
					while(chars.size() > charsCopy.size())
					{
						chars.remove(0);
					}
					return true;
				}
			}
		}
		return false;
	}
}
