package dev.theturkey.aoc2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 extends AOCPuzzle
{
	public Day23()
	{
		super("23");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		String[] inputInts = inputStrs.get(0).split("");

		//Part 1
		Map<Integer, CustomLinkedNode> cups = new HashMap<>();
		CustomLinkedNode firstCup = null;
		CustomLinkedNode currentCup = null;
		CustomLinkedNode one = null;
		for(int i = 0; i < inputInts.length; i++)
		{
			int num = Integer.parseInt(inputInts[i]);
			CustomLinkedNode node = new CustomLinkedNode(num);
			if(i == 0)
			{
				firstCup = node;
				currentCup = firstCup;
			}
			else
			{
				currentCup.next = node;
				currentCup = currentCup.next;
			}
			cups.put(num, node);

			if(num == 1)
				one = node;
		}
		currentCup.next = firstCup;

		currentCup = firstCup;
		run(100, currentCup, one, cups);

		//Part 2 Not working, but I'm close?
		cups = new HashMap<>();
		for(int i = 0; i < inputInts.length; i++)
		{
			int num = Integer.parseInt(inputInts[i]);
			CustomLinkedNode node = new CustomLinkedNode(num);
			if(i == 0)
			{
				firstCup = node;
				currentCup = firstCup;
			}
			else
			{
				currentCup.next = node;
				currentCup = currentCup.next;
			}
			cups.put(num, node);

			if(num == 1)
				one = node;
		}
		for(int i = 10; i <= 1_000_000; i++)
		{
			CustomLinkedNode node = new CustomLinkedNode(i);
			cups.put(i, node);
			currentCup.next = node;
			currentCup = currentCup.next;
		}
		currentCup.next = firstCup;
		currentCup = firstCup;
		run(10_000_000, currentCup, one, cups);
	}

	public void run(int runAmount, CustomLinkedNode currentCup, CustomLinkedNode one, Map<Integer, CustomLinkedNode> cups)
	{
		for(int i = 0; i < runAmount; i++)
		{
			CustomLinkedNode removed = currentCup.next;
			currentCup.next = currentCup.next.next.next.next;

			int destNumber = currentCup.value == 1 ? 9 : currentCup.value - 1;
			while(removed.value == destNumber || removed.next.value == destNumber || removed.next.next.value == destNumber)
			{
				destNumber--;
				if(destNumber == 0)
					destNumber = 9;
			}

			CustomLinkedNode insertNode = cups.get(destNumber);
			removed.next.next.next = insertNode.next;
			insertNode.next = removed;

			currentCup = currentCup.next;
		}

		currentCup = one;
		if(runAmount == 100)
		{
			StringBuilder stringBuilder = new StringBuilder();
			for(int i = 0; i < cups.size() - 1; i++)
			{
				currentCup = currentCup.next;
				stringBuilder.append(currentCup.value);
			}
			lap(stringBuilder.toString());
		}
		else
		{
			long num1 = currentCup.next.value;
			long num2 = currentCup.next.next.value;
			System.out.println(num1 + " " + num2);
			lap(num1 * num2);
		}


	}

	private static class CustomLinkedNode
	{
		public int value;
		public CustomLinkedNode next;

		public CustomLinkedNode(int value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return "CustomLinkedNode{" + value + '}';
		}
	}
}
