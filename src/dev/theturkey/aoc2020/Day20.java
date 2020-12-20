package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day20 extends AOCPuzzle
{
	public Day20()
	{
		super("20");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		List<TileData> tiles = new ArrayList<>();
		TileData tileData = null;
		String last = "";
		for(String s : inputStrs)
		{
			if(s.startsWith("Tile "))
			{
				tileData = new TileData();
				tileData.id = Integer.parseInt(s.substring(5, s.indexOf(":")));
			}
			else if(s.trim().isEmpty())
			{
				tileData.botEdge = last;
				last = "";
				tiles.add(tileData);
			}
			else
			{
				if(last.isEmpty())
				{
					tileData.topEdge = s;
				}
				last = s;
				tileData.leftEdge = tileData.leftEdge + s.substring(0, 1);
				tileData.rightEdge = tileData.rightEdge + s.substring(s.length() - 1);
			}
		}
		tiles.add(tileData);

		long answer = 1;
		int t = 0;
		for(int i = 0; i < tiles.size(); i++)
		{
			TileData td1 = tiles.get(i);

			boolean top = false;
			boolean left = false;
			boolean bottom = false;
			boolean right = false;
			for(int j = 0; j < tiles.size(); j++)
			{
				if(i == j)
					continue;

				TileData td2 = tiles.get(j);

				if(stringsMatch(td1.topEdge, td2.botEdge) || stringsMatch(td1.topEdge, td2.topEdge) || stringsMatch(td1.topEdge, td2.leftEdge) || stringsMatch(td1.topEdge, td2.rightEdge))
				{
					top = true;
				}
				if(stringsMatch(td1.botEdge, td2.botEdge) || stringsMatch(td1.botEdge, td2.topEdge) || stringsMatch(td1.botEdge, td2.leftEdge) || stringsMatch(td1.botEdge, td2.rightEdge))
				{
					bottom = true;
				}
				if(stringsMatch(td1.leftEdge, td2.botEdge) || stringsMatch(td1.leftEdge, td2.topEdge) || stringsMatch(td1.leftEdge, td2.leftEdge) || stringsMatch(td1.leftEdge, td2.rightEdge))
				{
					left = true;
				}
				if(stringsMatch(td1.rightEdge, td2.botEdge) || stringsMatch(td1.rightEdge, td2.topEdge) || stringsMatch(td1.rightEdge, td2.leftEdge) || stringsMatch(td1.rightEdge, td2.rightEdge))
				{
					right = true;
				}
			}

			int count = (top ? 0 : 1) + (bottom ? 0 : 1) + (left ? 0 : 1) + (right ? 0 : 1);
			System.out.println(count);
			if(count == 2)
			{
				answer *= td1.id;
				t++;
			}
		}

		lap(t);
		lap(answer);
	}

	public boolean stringsMatch(String s1, String s2)
	{
		return s1.equals(s2) || s1.equals(reverseString(s2));
	}

	public static String reverseString(String s)
	{
		StringBuilder toReturn = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--)
		{
			char b = s.charAt(i);
			toReturn.append(b);
		}
		return toReturn.toString();
	}

	private static class TileData
	{
		public int id;
		public String topEdge = "";
		public String botEdge = "";
		public String rightEdge = "";
		public String leftEdge = "";
	}
}
