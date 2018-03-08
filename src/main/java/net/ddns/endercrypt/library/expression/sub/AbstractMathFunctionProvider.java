package net.ddns.endercrypt.library.expression.sub;

import java.util.Optional;
import java.util.function.Function;

import net.ddns.endercrypt.library.expression.MathPriority;
import net.ddns.endercrypt.library.expression.constructor.MathConstructor;
import net.ddns.endercrypt.library.expression.constructor.MathObjectConstructorProvider;
import net.ddns.endercrypt.library.expression.container.MathOperatorContainer;
import net.ddns.endercrypt.library.expression.math.number.MathNumberObject;

public abstract class AbstractMathFunctionProvider extends MathObjectConstructorProvider
{
	private String name;
	private Function<Double, Double> function;

	public AbstractMathFunctionProvider(String name, Function<Double, Double> function)
	{
		this.name = name;
		this.function = function;
	}

	@Override
	public MathConstructor makeMathConstructor()
	{
		return new MathConstructor()
		{
			@Override
			protected boolean validate(String text)
			{
				return name.startsWith(text.toLowerCase());
			}

			@Override
			protected MathObject internalBuild(String text)
			{
				return new MathFunctionObject();
			}

			@Override
			public MathPriority getPriority()
			{
				return MathPriority.FUNCTION;
			}
		};
	}

	private class MathFunctionObject extends MathObject implements ImplodableMathOperator
	{
		@Override
		public MathPriority getPriority()
		{
			return MathPriority.FUNCTION;
		}

		@Override
		protected Optional<String> info()
		{
			return Optional.of("Func=" + name);
		}

		@Override
		public void implode(MathOperatorContainer objects, int position)
		{
			MathOperatorContainer.Indexer indexer = objects.new Indexer();
			indexer.setIndex(position);
			SolveableMathObject inputObject = indexer.pullRight(SolveableMathObject.class);
			double input = inputObject.solve();

			double output = function.apply(input);

			indexer.replace(new MathNumberObject(output));
		}
	}
}
