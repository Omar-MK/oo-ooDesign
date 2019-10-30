/**
 * The Beaver class allows for the creation of Beaver Consumer Objects which
 * are a type of AbstractItem. Beavers have a storage capacity of 50 nutrtion
 * units, and consume 5 units of nutrition per timeStep.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class Beaver extends Consumer
{
	/**
	 * The maximum nutrition a Beaver can store at it's location.
	 */
	private final int storageCap = 50;

	/**
	 * Creates a Beaver Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the Beaver Object on
	 * @param xCoordinate - the x coordinate of this Beaver Object on the grid
	 * @param yCoordinate - the y coordinate of this Beaver Object on the grid
	 */
	public Beaver(Grid grid, int xCoordinate, int yCoordinate)
	{
		super(grid, xCoordinate, yCoordinate);
		consumption = 5;
	}

	/**
	 * Processes the Beaver state for the given timeStep.
	 * This includes making the Beaver consume upto 5 nutritional units based
	 * on the availability of food every turn. Excess food is stored up to a
	 * capacity of 50 units, after which anything else is wasted.
	 *
	 * @param timeStep - the game time or turn.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		super.process(timeStep);
		if (getStock() > 50)
		{
			grid.setStockAt(xCoordinate, yCoordinate, 50);
		}
	}

	/**
	 * Returns a string about the nutrtion at this Beaver Object location on
	 * the grid.
	 * String takes the form: Beaver(nutrition).
	 *
	 * @return the nutrtion at this Beaver Object location on the grid
	 */
	@Override
	public String toString()
	{
		return "Beaver(" + getStock() + ")";
	}
}
