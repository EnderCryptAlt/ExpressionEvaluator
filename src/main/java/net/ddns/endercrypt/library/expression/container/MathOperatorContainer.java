package net.ddns.endercrypt.library.expression.container;

import java.util.ArrayList;
import java.util.Collection;

import net.ddns.endercrypt.library.expression.sub.MathObject;

public class MathOperatorContainer extends ArrayList<MathObject>
{
	public MathOperatorContainer()
	{
		super();
	}

	public MathOperatorContainer(Collection<? extends MathObject> c)
	{
		super(c);
	}

	public boolean isClass(int index, Class<?> clazz)
	{
		//clazz.asSubclass(clazz)
		return clazz.isInstance(get(index));
	}

	public <T> T getClass(int index, Class<T> clazz)
	{
		return clazz.cast(get(index));
	}

	public class Indexer
	{
		private int index;

		// INDEX

		public void setIndex(int index)
		{
			this.index = index;
		}

		public int getIndex()
		{
			return index;
		}

		// REPLACE

		public void replace(MathObject replacement)
		{
			MathOperatorContainer.this.set(index, replacement);
		}

		// LEFT

		public MathObject pullLeft()
		{
			index--;
			return MathOperatorContainer.this.remove(index);
		}

		public <T extends MathObject> T pullLeft(Class<T> clazz)
		{
			return clazz.cast(pullLeft());
		}

		// RIGHT

		public MathObject pullRight()
		{
			return MathOperatorContainer.this.remove(index + 1);
		}

		public <T extends MathObject> T pullRight(Class<T> clazz)
		{
			return clazz.cast(pullRight());
		}
	}
}
