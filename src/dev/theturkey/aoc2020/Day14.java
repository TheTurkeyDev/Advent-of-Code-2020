package dev.theturkey.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 extends AOCPuzzle
{
	public Day14()
	{
		super("14");
	}

	@Override
	public void solve(List<String> inputStrs)
	{
		//PART 1
		char[] bitMask = new char[36];
		Map<String, Long> memory = new HashMap<>();
		for(String s : inputStrs)
		{
			String[] parts = s.split("=");
			if(parts[0].trim().equals("mask"))
			{
				bitMask = parts[1].trim().toCharArray();
			}
			else
			{
				int index = Integer.parseInt(parts[0].substring(4, parts[0].length() - 2));
				long result = 0;
				String number = String.format("%36s", Integer.toBinaryString(Integer.parseInt(parts[1].trim()))).replace(" ", "0");
				for(int i = 0; i < bitMask.length; i++)
				{
					if(bitMask[i] != 'X')
						result = (result << 1) | Integer.parseInt(String.valueOf(bitMask[i]));
					else
						result = (result << 1) | Integer.parseInt(String.valueOf(number.charAt(i)));
				}

				memory.put(String.valueOf(index), result);
			}
		}

		long sum = 0;
		for(Long value : memory.values())
		{
			sum += value;
		}
		lap(sum);


		//PART 2
		memory = new HashMap<>();
		for(String s : inputStrs)
		{
			String[] parts = s.split("=");
			if(parts[0].trim().equals("mask"))
			{
				bitMask = parts[1].trim().toCharArray();
			}
			else
			{
				List<char[]> address = new ArrayList<>();
				int index = Integer.parseInt(parts[0].substring(4, parts[0].length() - 2));
				address.add(String.format("%36s", Integer.toBinaryString(index)).replace(" ", "0").toCharArray());

				long number = Integer.parseInt(parts[1].trim());
				for(int i = 0; i < bitMask.length; i++)
				{
					for(int j = address.size() - 1; j >= 0; j--)
					{
						char[] addressEdit = address.remove(j);
						if(bitMask[i] == 'X')
						{
							addressEdit[i] = '0';
							address.add(addressEdit);
							char[] clone = addressEdit.clone();
							clone[i] = '1';
							address.add(clone);
						}
						else if(bitMask[i] == '1')
						{
							addressEdit[i] = '1';
							address.add(addressEdit);
						}
						else
						{
							address.add(addressEdit);
						}
					}
				}

				for(char[] addr : address)
					memory.put(new String(addr), number);
			}
		}

		sum = 0;
		for(Long value : memory.values())
		{
			sum += value;
		}
		lap(sum);
	}
}
