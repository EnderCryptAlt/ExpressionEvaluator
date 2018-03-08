package net.ddns.endercrypt.library.expression.math.basic;

import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorObject;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;

public class MathSubtractionProvider extends AbstractMathOperatorProvider
{
	public MathSubtractionProvider()
	{
		super('-', MathPriority.BASIC_MATH);
	}

	@Override
	protected MathObject constructOperatorObject()
	{
		return new MathSubtractionObject();
	}

	private static class MathSubtractionObject extends AbstractMathOperatorObject
	{
		public MathSubtractionObject()
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
			return left - right;
		}
	}
}
