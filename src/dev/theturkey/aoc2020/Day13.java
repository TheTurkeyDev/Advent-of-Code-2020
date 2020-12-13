package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day13 extends AOCPuzzle
{
	public Day13()
	{
		super("13");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		long leaveTime = Long.parseLong(inputStrs.get(0));
		List<Integer> busIDs = new ArrayList<>();
		for(String id : inputStrs.get(1).split(","))
		{
			if(!id.equals("x"))
			{
				busIDs.add(Integer.parseInt(id));
			}
		}

		int smallID = 1;
		long smallest = Integer.MAX_VALUE;
		for(Integer id : busIDs)
		{
			if(leaveTime % id != 0)
			{
				long ciel = (int) (Math.ceil(leaveTime / (double) id) * id);
				if(ciel < smallest)
				{
					smallest = ciel;
					smallID = id;
				}
			}
			else
			{
				lap(0);
			}
		}

		lap((smallest - leaveTime) * smallID);


		// PART 2 uses the Chinese Remainder Theorem... I don't know it though...
		// The below doesn't use CRT as isn't my own work. It's a solution that was hashed out with the help of others
		busIDs = new ArrayList<>();

		for(String id : inputStrs.get(1).split(","))
		{
			if(id.equals("x"))
				busIDs.add(0);
			else
				busIDs.add(Integer.parseInt(id));
		}

		long step = 1;
		long out = 0;
		for(int i = 0; i < busIDs.size(); i++)
		{
			int busID = busIDs.get(i);
			if(busID == 0)
				continue;

			while((out + i) % busID != 0)
				out += step;

			step *= busID;
		}
		lap(out);
	}
}
