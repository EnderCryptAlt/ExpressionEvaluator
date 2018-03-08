package net.ddns.endercrypt.library.expression.sub;

import net.ddns.endercrypt.library.expression.container.MathOperatorContainer;
import net.ddns.endercrypt.library.expression.math.number.MathNumberObject;

public abstract class AbstractMathOperatorObject extends MathObject implements ImplodableMathOperator
{
	public AbstractMathOperatorObject()
	{

	}

	@Override
	public void implode(MathOperatorContainer objects, int position)
	{
		MathOperatorContainer.Indexer indexer = objects.new Indexer();
		indexer.setIndex(position);

		SolveableMathObject left = indexer.pullLeft(SolveableMathObject.class);
		double leftValue = left.solve();

		SolveableMathObject right = indexer.pullRight(SolveableMathObject.class);
		double rightValue = right.solve();

		indexer.replace(new MathNumberObject(solveOperator(leftValue, rightValue)));
	}

	protected abstract double solveOperator(double left, double right);
}
