package net.ddns.endercrypt.library.expression.math.advanced;

import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorObject;
import net.ddns.endercrypt.library.expression.sub.AbstractMathOperatorProvider;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.MathPriority;

public class MathDivisionProvider extends AbstractMathOperatorProvider
{
	public MathDivisionProvider()
	{
		super('/', MathPriority.ADVANCE_MATH);
	}

	@Override
	protected MathObject constructOperatorObject()
	{
		return new MathDivisionObject();
	}

	private static class MathDivisionObject extends AbstractMathOperatorObject
	{
		public MathDivisionObject()
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
			return left / right;
		}
	}
}
