package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day17 extends AOCPuzzle
{
	//Don't even waste you time. Don't look at this code....
	public Day17()
	{
		super("17");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		World initWorld = new World();
		for(int y = 0; y < inputStrs.size(); y++)
		{
			String s = inputStrs.get(y);
			for(int x = 0; x < s.length(); x++)
			{
				initWorld.addLocation(new Location(x, y, 0, 0, s.charAt(x)));
			}
		}

		//PART 1:
		World world = initWorld;
		for(int i = 0; i < 6; i++)
		{
			World newWorld = new World();
			int[] zRange = world.getZVals();
			int[] yRange = world.getYVals();
			int[] xRange = world.getXVals();
			for(int z = zRange[0] - 1; z <= zRange[1] + 1; z++)
			{
				for(int y = yRange[0] - 1; y <= yRange[1] + 1; y++)
				{
					for(int x = xRange[0] - 1; x <= xRange[1] + 1; x++)
					{
						int active = world.getNeighborActiveCount(x, y, z, 0);


						if(world.isActive(x, y, z, 0))
						{
							if(active == 2 || active == 3)
							{
								newWorld.addLocation(new Location(x, y, z, 0, '#'));
							}
							else
							{
								newWorld.addLocation(new Location(x, y, z, 0, '.'));
							}
						}
						else
						{
							if(active == 3)
							{
								newWorld.addLocation(new Location(x, y, z, 0, '#'));
							}
							else
							{
								newWorld.addLocation(new Location(x, y, z, 0, '.'));
							}
						}

					}
				}
			}
			world = newWorld;
		}

		lap(world.getTotalActive());

		//PART 2:
		world = initWorld;
		for(int i = 0; i < 6; i++)
		{
			World newWorld = new World();
			int[] wRange = world.getWVals();
			int[] zRange = world.getZVals();
			int[] yRange = world.getYVals();
			int[] xRange = world.getXVals();
			for(int w = wRange[0] - 1; w <= wRange[1] + 1; w++)
			{
				for(int z = zRange[0] - 1; z <= zRange[1] + 1; z++)
				{
					for(int y = yRange[0] - 1; y <= yRange[1] + 1; y++)
					{
						for(int x = xRange[0] - 1; x <= xRange[1] + 1; x++)
						{
							int active = world.getNeighborActiveCount(x, y, z, w);

							if(world.isActive(x, y, z, w))
							{
								if(active == 2 || active == 3)
								{
									newWorld.addLocation(new Location(x, y, z, w, '#'));
								}
								else
								{
									newWorld.addLocation(new Location(x, y, z, w, '.'));
								}
							}
							else
							{
								if(active == 3)
								{
									newWorld.addLocation(new Location(x, y, z, w, '#'));
								}
								else
								{
									newWorld.addLocation(new Location(x, y, z, w, '.'));
								}
							}

						}
					}
				}
			}
			world = newWorld;
		}

		lap(world.getTotalActive());
	}

	private static class World
	{
		private List<Location> locations = new ArrayList<>();

		public void addLocation(Location loc)
		{
			locations.add(loc);
		}

		public boolean isActive(int x, int y, int z, int w)
		{
			for(Location location : locations)
				if(location.x == x && location.y == y && location.z == z && location.w == w)
					return location.c == '#';
			return false;
		}

		public int getTotalActive()
		{
			int active = 0;
			for(Location location : locations)
				if(location.c == '#')
					active++;
			return active;
		}

		public int getNeighborActiveCount(int x, int y, int z, int w)
		{
			int active = 0;
			for(int wOff = -1; wOff < 2; wOff++)
			{
				for(int zOff = -1; zOff < 2; zOff++)
				{
					for(int yOff = -1; yOff < 2; yOff++)
					{
						for(int xOff = -1; xOff < 2; xOff++)
						{
							if(zOff == 0 && yOff == 0 && xOff == 0 && wOff == 0)
								continue;

							if(isActive(x + xOff, y + yOff, z + zOff, w + wOff))
							{
								active++;
								if(active > 3)
									return active;
							}
						}
					}
				}
			}
			return active;
		}

		public int[] getXVals()
		{
			int[] toReturn = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

			for(Location location : locations)
			{
				if(location.x < toReturn[0])
				{
					toReturn[0] = location.x;
				}

				if(location.x > toReturn[1])
				{
					toReturn[1] = location.x;
				}
			}

			return toReturn;
		}

		public int[] getYVals()
		{
			int[] toReturn = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

			for(Location location : locations)
			{
				if(location.y < toReturn[0])
				{
					toReturn[0] = location.y;
				}

				if(location.y > toReturn[1])
				{
					toReturn[1] = location.y;
				}
			}

			return toReturn;
		}

		public int[] getZVals()
		{
			int[] toReturn = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

			for(Location location : locations)
			{
				if(location.z < toReturn[0])
				{
					toReturn[0] = location.z;
				}

				if(location.z > toReturn[1])
				{
					toReturn[1] = location.z;
				}
			}

			return toReturn;
		}

		public int[] getWVals()
		{
			int[] toReturn = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};

			for(Location location : locations)
			{
				if(location.w < toReturn[0])
				{
					toReturn[0] = location.w;
				}

				if(location.w > toReturn[1])
				{
					toReturn[1] = location.w;
				}
			}

			return toReturn;
		}
	}

	private static class Location
	{
		public int x;
		public int y;
		public int z;
		public int w;
		public char c;

		public Location(int x, int y, int z, int w, char c)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
			this.c = c;
		}
	}
}
