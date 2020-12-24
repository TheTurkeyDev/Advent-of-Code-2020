package dev.theturkey.aoc2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day24 extends AOCPuzzle
{
	public Day24()
	{
		super("24");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		Map<Vect2I, Boolean> tiles = new HashMap<>();
		Vect2I current;
		for(String s : inputStrs)
		{
			current = new Vect2I(0, 0);
			int i = 0;
			while(i < s.length())
			{
				String sub = "" + s.charAt(i);
				if(s.charAt(i) == 'n' || s.charAt(i) == 's')
				{
					i++;
					sub += s.charAt(i);
				}
				moveVec(current, sub);
				i++;
			}

			if(tiles.containsKey(current))
				tiles.replace(current, !tiles.get(current));
			else
				tiles.put(current, false);
		}

		int blackSideUpCount = 0;
		for(Vect2I vect2I : tiles.keySet())
			if(!tiles.get(vect2I))
				blackSideUpCount++;
		lap(blackSideUpCount);

		Map<Vect2I, Boolean> tilesCopy;
		for(int y = -70; y < 70; y++)
			for(int x = -70; x < 70; x++)
				tiles.putIfAbsent(new Vect2I(x, y), true);

		List<String> dirs = Arrays.asList("e", "w", "nw", "ne", "se", "sw");
		for(int i = 0; i < 100; i++)
		{
			tilesCopy = new HashMap<>(tiles);
			for(Vect2I vect2I : tiles.keySet())
			{
				int black = 0;
				for(String dir : dirs)
				{
					Vect2I copy = vect2I.clone();
					moveVec(copy, dir);
					if(tiles.containsKey(copy) && !tiles.get(copy))
						black++;
				}

				if(tiles.get(vect2I) && black == 2)
					tilesCopy.put(vect2I, false);
				else if(!tiles.get(vect2I) && (black == 0 | black > 2))
					tilesCopy.put(vect2I, true);
			}
			tiles = tilesCopy;
		}

		blackSideUpCount = 0;
		for(Vect2I vect2I : tiles.keySet())
			if(!tiles.get(vect2I))
				blackSideUpCount++;

		lap(blackSideUpCount);
	}

	public void moveVec(Vect2I vect2I, String s)
	{
		int i = 0;
		if(s.charAt(i) == 'e')
		{
			vect2I.add(1, 0);
		}
		else if(s.charAt(i) == 'w')
		{
			vect2I.add(-1, 0);
		}
		else if(s.charAt(i) == 'n')
		{
			i++;
			boolean even = vect2I.y % 2 == 0;
			vect2I.add(0, -1);
			if(s.charAt(i) == 'e' && !even)
				vect2I.add(1, 0);
			else if(s.charAt(i) == 'w' && even)
				vect2I.add(-1, 0);
		}
		else if(s.charAt(i) == 's')
		{
			i++;
			boolean even = vect2I.y % 2 == 0;
			vect2I.add(0, 1);
			if(s.charAt(i) == 'e' && !even)
				vect2I.add(1, 0);
			else if(s.charAt(i) == 'w' && even)
				vect2I.add(-1, 0);
		}
	}

	public static class Vect2I
	{
		public int x;
		public int y;

		public Vect2I(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public void add(int x, int y)
		{
			this.x += x;
			this.y += y;
		}

		@Override
		public boolean equals(Object o)
		{
			if(this == o) return true;
			if(o == null || getClass() != o.getClass()) return false;
			Vect2I vect2I = (Vect2I) o;
			return x == vect2I.x &&
					y == vect2I.y;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(x, y);
		}

		@Override
		public String toString()
		{
			return "Vect2I{" +
					"x=" + x +
					", y=" + y +
					'}';
		}

		@Override
		protected Vect2I clone()
		{
			return new Vect2I(x, y);
		}
	}
}
