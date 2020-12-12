package dev.theturkey.aoc2020;

import java.util.List;

public class Day12 extends AOCPuzzle
{
	public Day12()
	{
		super("12");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		//Part 1
		int x = 0;
		int y = 0;
		Direction facing = Direction.EAST;

		for(String s : inputStrs)
		{
			int amount = Integer.parseInt(s.substring(1));
			switch(s.charAt(0))
			{
				case 'N':
					y += amount;
					break;
				case 'E':
					x += amount;
					break;
				case 'S':
					y -= amount;
					break;
				case 'W':
					x -= amount;
					break;
				case 'F':
					x += facing.xOff * amount;
					y += facing.yOff * amount;
					break;
				case 'L':
					facing = Direction.rotate(facing, amount / 90, false);
					break;
				case 'R':
					facing = Direction.rotate(facing, amount / 90, true);
					break;
			}
		}

		lap(Math.abs(x) + Math.abs(y));


		//PART 2

		int sx = 0;
		int sy = 0;
		int wx = 10;
		int wy = 1;

		for(String s : inputStrs)
		{
			int amount = Integer.parseInt(s.substring(1));
			switch(s.charAt(0))
			{
				case 'N':
					wy += amount;
					break;
				case 'E':
					wx += amount;
					break;
				case 'S':
					wy -= amount;
					break;
				case 'W':
					wx -= amount;
					break;
				case 'F':
					sx = sx + wx * amount;
					sy = sy + wy * amount;
					break;
				case 'L':
					for(int i = 0; i < amount / 90; i++)
					{
						int temp = wy;
						wy = wx;
						wx = temp * -1;
					}
					break;
				case 'R':
					for(int i = 0; i < amount / 90; i++)
					{
						int temp = wy;
						wy = wx * -1;
						wx = temp;
					}
					break;
			}
		}

		lap(Math.abs(sx) + Math.abs(sy));
	}

	public enum Direction
	{
		NORTH(0, 1),
		EAST(1, 0),
		SOUTH(0, -1),
		WEST(-1, 0);

		int xOff;
		int yOff;

		Direction(int xOff, int yOff)
		{
			this.xOff = xOff;
			this.yOff = yOff;
		}

		public static Direction rotate(Direction start, int amount, boolean clockwise)
		{
			Direction toReturn = start;
			for(int i = 0; i < amount; i++)
			{
				switch(toReturn)
				{
					case NORTH:
						toReturn = clockwise ? EAST : WEST;
						break;
					case EAST:
						toReturn = clockwise ? SOUTH : NORTH;
						break;
					case SOUTH:
						toReturn = clockwise ? WEST : EAST;
						break;
					case WEST:
						toReturn = clockwise ? NORTH : SOUTH;
						break;
				}
			}
			return toReturn;
		}
	}
}
