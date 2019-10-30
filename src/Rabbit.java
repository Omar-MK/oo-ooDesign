/**
 * The Rabbit class allows for the creation of Rabbit Consumer Objects which
 * are a type of AbstractItem. Rabbits have cannot store food, and can consume
 * 8 units of nutrition per timeStep.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class Rabbit extends Consumer
{
	/**
	 * Creates a Rabbit Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the Rabbit Object on
	 * @param xCoordinate - the x coordinate of this Rabbit Object on the grid
	 * @param yCoordinate - the y coordinate of this Rabbit Object on the grid
	 */
	public Rabbit(Grid grid, int xCoordinate, int yCoordinate)
	{
		super(grid, xCoordinate, yCoordinate);
		consumption = 8;
	}

	/**
	 * This method processes the Rabbit Object state at each timeStep. Causes
	 * the Rabbit to consume upto 8 nutritional units based on the availability
	 * of food every turn.
	 * Excess food is wasted.
	 *
	 * @param timeStep - the game time so far.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		super.process(timeStep);
		grid.emptyStockAt(xCoordinate, yCoordinate);
	}

	/**
	 * Returns a string about the nutrtion at this Rabbit Object location on
	 * the grid.
	 * String takes the form: Rabbit(nutrition). Rabbits waste food they cannot
	 * eat. So, nutrition at each step should be 0.
	 *
	 * @return the nutrtion at this Rabbit Object location on the grid
	 */
	@Override
	public String toString()
	{
		return "Rabbit(" + getStock() + ")";
	}
}
