package net.ddns.endercrypt.library.expression.math.extression;

import org.apache.commons.lang3.StringUtils;

import net.ddns.endercrypt.library.expression.MathEvaluator;
import net.ddns.endercrypt.library.expression.MathException;
import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.container.MathOperatorContainer;
import net.ddns.endercrypt.library.expression.math.number.MathNumberObject;
import net.ddns.endercrypt.library.expression.sub.ImplodableMathOperator;
import net.ddns.endercrypt.library.expression.sub.MathObject;
import net.ddns.endercrypt.library.expression.sub.SolveableMathObject;

public class MathExpressionObject extends SolveableMathObject implements ImplodableMathOperator
{
	private MathOperatorContainer objects;

	public MathExpressionObject(String text)
	{
		text = text.trim();
		int start = StringUtils.countMatches(text, '(');
		int end = StringUtils.countMatches(text, ')');
		if (start != end)
		{
			int diff = start - end;
			String info = (diff < 0) ? (-diff + " (") : (diff + " )");
			throw new MathException("expected " + info + " in \"" + text + "\"");
		}
		System.out.println("Processing: " + text);
		objects = MathEvaluator.extract(text);
		System.out.println("START: \"" + text + "\"");
		for (MathObject mathObject : objects)
		{
			System.out.println("\t" + mathObject.toString());
		}
		System.out.println("STOP");
	}

	@Override
	public void setVariable(String name, double value)
	{
		for (MathObject mathObject : objects)
		{
			mathObject.setVariable(name, value);
		}
	}

	@Override
	public double solve()
	{
		MathOperatorContainer temporaryObjects = new MathOperatorContainer(objects);
		while (temporaryObjects.size() != 1)
		{
			// find starting point
			int bestPriority = -1;
			int bestIndex = -1;
			for (int i = 0; i < temporaryObjects.size(); i++)
			{
				if (temporaryObjects.isClass(i, ImplodableMathOperator.class))
				{
					MathObject object = temporaryObjects.get(i);
					int priority = object.getPriority().ordinal();
					if (priority > bestPriority)
					{
						bestPriority = priority;
						bestIndex = i;
					}
				}
			}
			// consume
			if ((bestIndex == -1) || (bestPriority == -1))
			{
				throw new RuntimeException();
			}
			ImplodableMathOperator implodable = temporaryObjects.getClass(bestIndex, ImplodableMathOperator.class);
			System.out.println("Before implode: " + temporaryObjects + " of index " + bestIndex);
			implodable.implode(temporaryObjects, bestIndex);
			System.out.println("After implode: " + temporaryObjects);
		}
		return temporaryObjects.getClass(0, SolveableMathObject.class).solve();
	}

	@Override
	public MathPriority getPriority()
	{
		return MathPriority.EXPRESSION;
	}

	@Override
	public void implode(MathOperatorContainer objects, int position)
	{
		MathOperatorContainer.Indexer indexer = objects.new Indexer();
		indexer.setIndex(position);
		indexer.replace(new MathNumberObject(solve()));
	}
}
