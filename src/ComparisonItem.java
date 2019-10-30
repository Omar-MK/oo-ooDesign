/**
 * The ComparisonItem class allows for the creation AbstractItem objects that
 * store only x and y coordinates. This is useful if the coordinates between this Object and any other AbstractItem Object need to be compares using the
 * equals method. All overloaded methods perform no functions.
 * @author 170018405
 * @version Oct 19, 2018
 */

public class ComparisonItem extends AbstractItem
{
	/**
	 * Creates a ComparisonItem Object with x and y coordinates.
	 *
	 * @param xCoordinate - the x coordinate of this Object
	 * @param yCoordinate - the y coordinate of this Object
	 */
	public ComparisonItem(int yCoordinate, int xCoordinate)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	/**
	 * Empty method.
	 */
 	@Override
	public void process(TimeStep timeStep)
	{

	}

	/**
	 * Empty method.
	 */
 	@Override
	protected int getStock()
	{
		return 0;
	}

	/**
	 * Empty method.
	 */
	@Override
	protected void addToStock(int nutrition)
	{

	}

	/**
	 * Empty method.
	 */
	@Override
	protected void reduceStock(int nutrition)
	{

	}

	/**
	 * Checks if an AbstractItem Object shares the same coordinates as this
	 * AbstractItem Object.
	 *
	 * @return true if both AbstractItem Objects share the same coordinates
	 */
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof AbstractItem &&
		((AbstractItem) o).xCoordinate == xCoordinate &&
		((AbstractItem) o).yCoordinate == yCoordinate) return true;
		return false;
	}
}
