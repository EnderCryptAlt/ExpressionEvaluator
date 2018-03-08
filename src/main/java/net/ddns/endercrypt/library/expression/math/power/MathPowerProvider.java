package net.ddns.endercrypt.library.expression.math.power;

import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorObject;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;

public class MathPowerProvider extends AbstractMathOperatorProvider
{
	public MathPowerProvider()
	{
		super('^', MathPriority.POWER);
	}

	@Override
	protected MathObject constructOperatorObject()
	{
		return new MathPowerObject();
	}

	private static class MathPowerObject extends AbstractMathOperatorObject
	{
		@Override
		public MathPriority getPriority()
		{
			return MathPriority.POWER;
		}

		@Override
		protected double solveOperator(double left, double right)
		{
			return Math.pow(left, right);
		}
	}
}
