package net.ddns.endercrypt.library.expression.math.number;

import java.util.Optional;

import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.sub.SolveableMathObject;

public class MathNumberObject extends SolveableMathObject
{
	private double value;

	public MathNumberObject(double value)
	{
		this.value = value;
	}

	@Override
	public MathPriority getPriority()
	{
		return MathPriority.NUMBER;
	}

	@Override
	public double solve()
	{
		return value;
	}

	@Override
	protected Optional<String> info()
	{
		return Optional.of("value=" + value);
	}
}
