package net.ddns.endercrypt.library.expression.sub;

import java.util.Optional;

import net.ddns.endercrypt.library.expression.MathPriority;

public abstract class MathObject
{
	public void setVariable(String name, double value)
	{
		// default: do nothing
	}

	public abstract MathPriority getPriority();

	protected Optional<String> info()
	{
		return Optional.empty();
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		info().ifPresent(info -> sb.append(" [").append(info).append("]"));
		return sb.toString();
	}
}
