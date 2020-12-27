package dev.theturkey.aoc2020;

import java.util.List;

public class Day25 extends AOCPuzzle
{
	public Day25()
	{
		super("25");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		int cardPubKey = Integer.parseInt(inputStrs.get(0));
		int doorPubKey = Integer.parseInt(inputStrs.get(1));
		int cardLoopSize = getLoopSize(cardPubKey);
		int doorLoopSize = getLoopSize(doorPubKey);
		long encryptionKey1 = 1;
		for(int i = 0; i < cardLoopSize; i++)
			encryptionKey1 = (encryptionKey1 * doorPubKey) % 20201227;

		long encryptionKey2 = 1;
		for(int i = 0; i < doorLoopSize; i++)
			encryptionKey2 = (encryptionKey2 * cardPubKey) % 20201227;

		if(encryptionKey1 == encryptionKey2)
			lap(encryptionKey1);
		else
			System.out.println("Uhhhhhhhhhhhhhhhhhhhhhh. You dun goofed");
	}


	public int getLoopSize(int publicKey)
	{
		int subNum = 7;
		int num = subNum;
		int loopSize = 1;
		while(num != publicKey)
		{
			loopSize++;
			num = (num * subNum) % 20201227;
		}
		return loopSize;
	}
}
