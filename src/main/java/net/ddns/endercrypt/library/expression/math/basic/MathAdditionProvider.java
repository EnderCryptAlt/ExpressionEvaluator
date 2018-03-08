package net.ddns.endercrypt.library.expression.math.basic;

import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorObject;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.MathPriority;

public class MathAdditionProvider extends AbstractMathOperatorProvider
{
	public MathAdditionProvider()
	{
		super('+', MathPriority.BASIC_MATH);
	}

	@Override
	protected MathObject constructOperatorObject()
	{
		return new MathAdditionObject();
	}

	private static class MathAdditionObject extends AbstractMathOperatorObject
	{
		public MathAdditionObject()
		{

		}

		@Override
		public MathPriority getPriority()
		{
			return MathPriority.BASIC_MATH;
		}

		@Override
		protected double solveOperator(double left, double right)
		{
			return left + right;
		}
	}
}
