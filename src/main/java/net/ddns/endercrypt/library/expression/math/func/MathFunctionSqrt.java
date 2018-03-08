package net.ddns.endercrypt.library.expression.math.func;

import net.ddns.endercrypt.library.expression.sub.AbstractMathFunctionProvider;

public class MathFunctionSqrt extends AbstractMathFunctionProvider
{
	public MathFunctionSqrt()
	{
		super("sqrt", (v) -> Math.sqrt(v));
	}
}
