package dev.theturkey.aoc2020;

import java.util.List;

public class Day18 extends AOCPuzzle
{
	public Day18()
	{
		super("18");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		long answer = 0;
		for(String s : inputStrs)
		{
			answer += solve(s.replace(" ", ""));
		}
		lap(answer);

		answer = 0;
		for(String s : inputStrs)
		{
			answer += solveAdd(s.replace(" ", ""));
		}
		lap(answer);
	}

	public long solve(String s)
	{
		if(!s.contains("+") && !s.contains("*"))
			return Long.parseLong(s);

		if(s.startsWith("("))
		{
			s = solveParen(s);
		}

		String num = readNextNumber(s, 0);
		long left = Long.parseLong(num);
		String rem = s.substring(num.length());
		char operation = rem.charAt(0);
		rem = rem.substring(1);
		if(rem.startsWith("("))
		{
			rem = solveParen(rem);
		}

		num = readNextNumber(rem, 0);
		if(!num.isEmpty())
		{
			long right = Long.parseLong(num);
			rem = rem.substring(num.length());
			rem = solveOp(left, right, operation) + rem;
		}
		else
		{
			long right = Long.parseLong(num);
			return solveOp(left, right, operation);
		}
		return solve(rem);
	}

	public String solveParen(String s)
	{
		int pCount = 1;
		int end = 0;
		while(pCount > 0)
		{
			end++;
			if(s.charAt(end) == ')')
			{
				pCount--;
			}
			else if(s.charAt(end) == '(')
			{
				pCount++;
			}
		}

		String toProcess = s.substring(1, end);
		String rest = "";
		if(end + 1 < s.length())
			rest = s.substring(end + 1).trim();

		return solve(toProcess) + rest;
	}


	public long solveAdd(String s)
	{
		int openIndex = s.indexOf("(");
		while(openIndex != -1)
		{
			s = s.substring(0, openIndex) + solveParenAdd(s.substring(openIndex));
			openIndex = s.indexOf("(");
		}

		int plusIndex = s.indexOf("+");
		while(plusIndex != -1)
		{
			String left = readLastNumber(s, plusIndex - 1);
			String right = readNextNumber(s, plusIndex + 1);
			String beginning = s.substring(0, plusIndex - left.length());
			s = beginning + (Long.parseLong(left) + Long.parseLong(right)) + s.substring(beginning.length() + left.length() + 1 + right.length());
			plusIndex = s.indexOf("+");
		}

		int multIndex = s.indexOf("*");
		while(multIndex != -1)
		{
			String right = readNextNumber(s, multIndex + 1);
			s = (Long.parseLong(s.substring(0, multIndex)) * Long.parseLong(right)) + s.substring(multIndex + right.length() + 1);
			multIndex = s.indexOf("*");
		}

		return Long.parseLong(s);
	}

	public String solveParenAdd(String s)
	{
		int pCount = 1;
		int end = 0;
		while(pCount > 0)
		{
			end++;
			if(s.charAt(end) == ')')
			{
				pCount--;
			}
			else if(s.charAt(end) == '(')
			{
				pCount++;
			}
		}

		String toProcess = s.substring(1, end);
		String rest = "";
		if(end + 1 < s.length())
			rest = s.substring(end + 1).trim();

		return solveAdd(toProcess) + rest;
	}


	public String readLastNumber(String str, int index)
	{
		int start = index;
		while(start >= 0 && str.charAt(start) - 48 >= 0 && str.charAt(start) - 48 < 10)
		{
			start--;
		}
		return str.substring(start + 1, index + 1);
	}

	public String readNextNumber(String str, int index)
	{
		int end = index;
		while(end < str.length() && str.charAt(end) - 48 >= 0 && str.charAt(end) - 48 < 10)
		{
			end++;
		}
		if(end == index)
			return "";
		return str.substring(index, end);
	}

	public long solveOp(long num1, long num2, char op)
	{
		switch(op)
		{
			case '+':
				return num1 + num2;
			default:
				return num1 * num2;
		}
	}

}
