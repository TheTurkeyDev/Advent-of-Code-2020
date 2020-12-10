package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.List;

public class Day8 extends AOCPuzzle
{

	public Day8()
	{
		super("8");
	}

	@Override
	public void solve(List<String> input)
	{
		Result res = runProgram(input);
		lap(res.acc);

		int index = 0;
		boolean run = true;
		String old = "";
		Result res2;
		do
		{
			boolean found = false;
			while(!found && index < input.size())
			{
				String op = input.get(index);
				String[] opAndArgs = op.split(" ");
				if(opAndArgs[0].equals("nop"))
				{
					found = true;
					old = input.get(index);
					input.set(index, old.replace("nop", "jmp"));
				}
				else if(opAndArgs[0].equals("jmp"))
				{
					found = true;
					old = input.get(index);
					input.set(index, old.replace("jmp", "nop"));
				}
				else
				{
					index++;
				}
			}
			res2 = runProgram(input);
			if(!res2.infinite)
			{
				run = false;
			}
			else
			{
				input.set(index, old);
			}
			index++;
		} while(run && index < input.size());

		lap(res2.acc);
	}

	public Result runProgram(List<String> input)
	{
		Result toReturn = new Result();
		int globalAcc = 0;
		int pc = 0;

		List<Integer> visitedPCs = new ArrayList<>();

		boolean run = true;
		while(run)
		{
			visitedPCs.add(pc);
			String op = input.get(pc);
			String[] opAndArgs = op.split(" ");
			switch(opAndArgs[0])
			{
				case "nop":
					pc++;
					break;
				case "acc":
					globalAcc += Integer.parseInt(opAndArgs[1]);
					pc++;
					break;
				case "jmp":
					pc += Integer.parseInt(opAndArgs[1]);
					break;
			}

			if(visitedPCs.contains(pc))
			{
				run = false;
				toReturn.infinite = true;
				toReturn.acc = globalAcc;
			}
			else if(pc >= input.size())
			{
				run = false;
				toReturn.infinite = false;
				toReturn.acc = globalAcc;
			}
		}

		return toReturn;
	}

	private static class Result
	{
		boolean infinite;
		int acc;
	}
}
