package net.ddns.endercrypt.library.expression.constructor;

import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.sub.MathObject;

public abstract class MathConstructor implements Comparable<MathConstructor>
{
	private String text = "";
	private int priority = getPriority().ordinal();

	public abstract MathPriority getPriority();

	public boolean setText(String newText)
	{
		if (validate(newText))
		{
			text = newText;
			return true;
		}
		return false;
	}

	protected abstract boolean validate(String text);

	public MathObject build()
	{
		return internalBuild(text);
	}

	protected abstract MathObject internalBuild(String text);

	@Override
	public int compareTo(MathConstructor other)
	{
		return Integer.compare(priority, other.priority);
	}
}
