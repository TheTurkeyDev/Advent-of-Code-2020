package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day22 extends AOCPuzzle
{
	public Day22()
	{
		super("22");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		List<Integer> playersDeck = new ArrayList<>();
		List<Integer> crabsDeck = new ArrayList<>();
		boolean loadPlayerCards = true;
		for(String s : inputStrs)
		{
			if(s.startsWith("Player 1:"))
			{
				loadPlayerCards = true;
			}
			else if(s.startsWith("Player 2:"))
			{
				loadPlayerCards = false;
			}
			else if(!s.isEmpty())
			{
				int card = Integer.parseInt(s);
				if(loadPlayerCards)
					playersDeck.add(card);
				else
					crabsDeck.add(card);
			}
		}

		List<Integer> playerPart1 = new ArrayList<>(playersDeck);
		List<Integer> crabPart1 = new ArrayList<>(crabsDeck);
		boolean playerWon = playGamePart1(playerPart1, crabPart1);

		List<Integer> cardsToCount = new ArrayList<>();
		if(playerWon)
			cardsToCount.addAll(playerPart1);
		else
			cardsToCount.addAll(crabPart1);

		long total = 0;
		for(int i = 0; i < cardsToCount.size(); i++)
		{
			total += cardsToCount.get(i) * (cardsToCount.size() - i);
		}

		lap(total);

		List<Integer> playerPart2 = new ArrayList<>(playersDeck);
		List<Integer> crabPart2 = new ArrayList<>(crabsDeck);
		playerWon = playGamePart2(playerPart2, crabPart2);
		cardsToCount = new ArrayList<>();
		if(playerWon)
			cardsToCount.addAll(playerPart2);
		else
			cardsToCount.addAll(crabPart2);

		total = 0;
		for(int i = 0; i < cardsToCount.size(); i++)
		{
			total += cardsToCount.get(i) * (cardsToCount.size() - i);
		}

		lap(total);
	}

	public boolean playGamePart1(List<Integer> playersDeck, List<Integer> crabsDeck)
	{
		while(playersDeck.size() > 0 && crabsDeck.size() > 0)
		{
			int playerCard = playersDeck.remove(0);
			int crabsCard = crabsDeck.remove(0);
			if(playerCard > crabsCard)
			{
				playersDeck.add(playerCard);
				playersDeck.add(crabsCard);
			}
			else
			{
				crabsDeck.add(crabsCard);
				crabsDeck.add(playerCard);
			}
		}

		return playersDeck.size() > 0;
	}

	public boolean playGamePart2(List<Integer> playersDeck, List<Integer> crabsDeck)
	{
		Map<String, String> prevDecks = new HashMap<>();
		while(playersDeck.size() > 0 && crabsDeck.size() > 0)
		{
			String playerDeckStr = playersDeck.toString();
			String crabDeckStr = playersDeck.toString();
			if(prevDecks.containsKey(playerDeckStr) && prevDecks.get(playerDeckStr).equals(crabDeckStr))
				return true;

			prevDecks.put(playerDeckStr, crabDeckStr);
			int playerCard = playersDeck.remove(0);
			int crabsCard = crabsDeck.remove(0);
			boolean playerWon = playerCard > crabsCard;
			if(playerCard <= playersDeck.size() && crabsCard <= crabsDeck.size())
				playerWon = playGamePart2(new ArrayList<>(playersDeck.subList(0, playerCard)), new ArrayList<>(crabsDeck.subList(0, crabsCard)));

			if(playerWon)
			{
				playersDeck.add(playerCard);
				playersDeck.add(crabsCard);
			}
			else
			{
				crabsDeck.add(crabsCard);
				crabsDeck.add(playerCard);
			}
		}

		return playersDeck.size() > 0;
	}
}
