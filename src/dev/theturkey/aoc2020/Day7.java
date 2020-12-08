package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 extends AOCPuzzle
{
	private Map<String, List<BagInfo>> bags;
	private Map<String, Boolean> bagCache;

	public Day7()
	{
		super("7");
	}

	@Override
	public void solve(List<String> input)
	{
		bags = new HashMap<>();
		bagCache = new HashMap<>();
		for(String s : input)
		{
			String[] split1 = s.substring(0, s.length() - 1).split("bags contain");
			String outterBag = split1[0].trim();
			String[] innerbags = split1[1].split(",");
			List<BagInfo> bagInfos = new ArrayList<>();
			for(String innerbag : innerbags)
			{
				innerbag = innerbag.replace("bags", "").replace("bag", "").trim();
				int index = innerbag.trim().indexOf(" ");
				String quantityStr = innerbag.substring(0, index);
				if(!quantityStr.equalsIgnoreCase("no"))
				{
					BagInfo bagInfo = new BagInfo();
					bagInfo.quantity = Integer.parseInt(quantityStr);
					bagInfo.bagName = innerbag.substring(index + 1);
					bagInfos.add(bagInfo);
				}
			}

			bags.put(outterBag, bagInfos);
		}
		System.out.print("Bags Parsed, ");
		lap();
		int count = 0;
		for(String bag : bags.keySet())
		{
			if(isGoldBagInBag(bag))
			{
				count++;
			}
		}
		System.out.print("Part 1: " + count + ", ");
		lap();
		System.out.print("Part 2: " + (countBagsInGoldBag("shiny gold") - 1) + ", ");

	}

	private boolean isGoldBagInBag(String bag)
	{
		if(bagCache.containsKey(bag))
			return bagCache.get(bag);

		for(BagInfo subBag : bags.get(bag))
		{
			if(subBag.bagName.equalsIgnoreCase("shiny gold"))
			{
				bagCache.put(bag, true);
				return true;
			}
		}

		for(BagInfo subBag : bags.get(bag))
		{
			if(isGoldBagInBag(subBag.bagName))
			{
				bagCache.put(bag, true);
				return true;
			}
		}

		bagCache.put(bag, false);
		return false;
	}

	private int countBagsInGoldBag(String bag)
	{
		List<BagInfo> bagList = bags.get(bag);
		int count = 1;
		for(BagInfo bagInfo : bagList)
			count += (bagInfo.quantity * countBagsInGoldBag(bagInfo.bagName));

		return count;
	}

	public static class BagInfo
	{
		public int quantity;
		public String bagName;
	}
}
