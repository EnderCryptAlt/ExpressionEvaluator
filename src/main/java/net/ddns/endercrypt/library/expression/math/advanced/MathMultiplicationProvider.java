package net.ddns.endercrypt.library.expression.math.advanced;

import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorObject;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.MathPriority;

public class MathMultiplicationProvider extends AbstractMathOperatorProvider
{
	public MathMultiplicationProvider()
	{
		super('*', MathPriority.ADVANCE_MATH);
	}

	@Override
	protected MathObject constructOperatorObject()
	{
		return new MathMultiplicationObject();
	}

	private static class MathMultiplicationObject extends AbstractMathOperatorObject
	{
		public MathMultiplicationObject()
		{

		}

		@Override
		public MathPriority getPriority()
		{
			return MathPriority.ADVANCE_MATH;
		}

		@Override
		protected double solveOperator(double left, double right)
		{
			return left * right;
		}
	}
}
