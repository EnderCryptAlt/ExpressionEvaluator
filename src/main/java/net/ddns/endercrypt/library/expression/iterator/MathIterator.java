package net.ddns.endercrypt.library.expression.iterator;

public class MathIterator
{
	private String text;
	private int startIndex = 0;
	private int endIndex = 0;

	public MathIterator(String text)
	{
		this.text = text;
	}

	public int getStartIndex()
	{
		return startIndex;
	}

	public int getEndIndex()
	{
		return endIndex;
	}

	public void forward()
	{
		if (endIndex >= text.length())
		{
			throw new MathIteratorException("cannot move forwards, already at end");
		}
		endIndex++;
	}

	public void back()
	{
		if (endIndex <= 0)
		{
			throw new MathIteratorException("cannot move backwards, already at start");
		}
		endIndex--;
	}

	public int length()
	{
		return text.length();
	}

	public void syncIndexes()
	{
		if (startIndex > endIndex)
		{
			throw new MathIteratorException("cannot move current index backwards, must be moved forwards");
		}
		startIndex = endIndex;
	}

	public boolean canForward()
	{
		return (endIndex < text.length());
	}

	public String extract()
	{
		return text.substring(startIndex, endIndex);
	}

	private void requireSync(String action)
	{
		if (startIndex != endIndex)
		{
			throw new MathIteratorException("cannot " + action + ", iterator must sync its indexes");
		}
	}

	public void autoTrim()
	{
		requireSync("autoTrim()");
		while (text.charAt(endIndex) == ' ')
		{
			endIndex++;
		}
		syncIndexes();
	}
}
