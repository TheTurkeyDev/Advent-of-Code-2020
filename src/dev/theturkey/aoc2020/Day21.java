package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day21 extends AOCPuzzle
{
	public Day21()
	{
		super("21");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		Map<String, List<String>> allergenList = new HashMap<>();
		List<List<String>> allIngredients = new ArrayList<>();
		Set<String> uniqueIngredients = new HashSet<>();
		for(String s : inputStrs)
		{
			List<String> ingredients = Arrays.asList(s.substring(0, s.indexOf("(contains ")).split(" "));
			allIngredients.add(ingredients);
			uniqueIngredients.addAll(ingredients);
			String[] allergens = s.substring(s.indexOf("(contains ") + 10, s.length() - 1).split(", ");

			for(String a : allergens)
			{
				if(allergenList.containsKey(a))
				{
					List<String> possibleAllergens = allergenList.get(a);
					for(int i = possibleAllergens.size() - 1; i >= 0; i--)
					{
						if(!ingredients.contains(possibleAllergens.get(i)))
						{
							possibleAllergens.remove(i);
						}
					}
				}
				else
				{
					allergenList.put(a, new ArrayList<>(ingredients));
				}
			}
		}

		Map<String, String> allergenMapping = new HashMap<>();

		while(allergenList.size() > 0)
		{
			String toRemove = "";
			for(String a : allergenList.keySet())
			{
				List<String> ingredients = allergenList.get(a);
				if(ingredients.size() == 1)
				{
					String ing = ingredients.get(0);
					allergenMapping.put(a, ing);
					toRemove = a;
					for(String a2 : allergenList.keySet())
					{
						allergenList.get(a2).remove(ing);
					}
					break;
				}
			}

			if(!toRemove.isEmpty())
				allergenList.remove(toRemove);
		}

		List<String> hasNoAllergens = new ArrayList<>(uniqueIngredients);
		for(String a : allergenMapping.values())
		{
			hasNoAllergens.remove(a);
		}

		int total = 0;
		for(List<String> ingredients : allIngredients)
		{
			for(String ing : hasNoAllergens)
			{
				if(ingredients.contains(ing))
					total++;
			}
		}

		lap(total);
		List<String> aSorted = new ArrayList<>(allergenMapping.keySet());
		aSorted.sort(String::compareTo);
		StringBuilder builder = new StringBuilder();
		for(String a : aSorted)
			builder.append(allergenMapping.get(a)).append(",");
		builder.deleteCharAt(builder.length() - 1);

		lap(builder.toString());
	}
}
