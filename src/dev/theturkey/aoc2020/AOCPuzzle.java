package dev.theturkey.aoc2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AOCPuzzle
{
	private long timerStart;

	public AOCPuzzle(String day)
	{
		File file = new File("res/day" + day + ".txt");
		if(!file.exists())
		{
			solve(new ArrayList<>());
			return;
		}

		BufferedReader reader;
		try
		{
			reader = new BufferedReader(new FileReader(file));
		} catch(FileNotFoundException e)
		{
			System.err.println("File not found!!");
			solve(new ArrayList<>());
			return;
		}

		List<String> inputLines = new ArrayList<>();
		try
		{
			String line;
			while((line = reader.readLine()) != null)
				inputLines.add(line);

			reader.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}

		timerStart = System.nanoTime();
		solve(inputLines);
		lap();
	}

	abstract void solve(List<String> input);

	public void lap()
	{
		long timeSpent = (System.nanoTime() - timerStart) / 1000;
		if(timeSpent < 1000)
			System.out.println("Duration: " + timeSpent + "µs");
		else
			System.out.println("Duration: " + (timeSpent / 1000.0) + "ms");
		timerStart = System.nanoTime();
	}

}
