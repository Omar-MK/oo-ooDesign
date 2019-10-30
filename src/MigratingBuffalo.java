import java.lang.Math;

/**
 * The MigratingBuffalo class allows for the creation of MigratingBuffalo
 * Consumer Objects which are a type of AbstractItem. MigratingBuffalos have
 * unlimited storage capcity, but 10% of the nutrition stored at their location
 * rots every turn. The Buffalo return from their migration every 10 turns and
 * can consume upto 80 units of nutrition.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class MigratingBuffalo extends Consumer
{
	/**
	 * The interval between each migration in timeSteps.
	 */
	private int migInterval;

	/**
	 * Creates a MigratingBuffalo Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the MigratingBuffalo Object on
	 * @param xCoordinate - the x coordinate of this Buffalo Object on the grid
	 * @param yCoordinate - the y coordinate of this Buffalo Object on the grid
	 */
	public MigratingBuffalo(Grid grid, int xCoordinate, int yCoordinate)
	{
		super(grid, xCoordinate, yCoordinate);
		consumption = 80;
		migInterval = 10;
	}

	/**
	 * Retuns the timeSteps between each migration.
	 *
	 * @return the timeSteps between each migration
	 */
	public int getMigInterval()
	{
		return migInterval;
	}

	/**
	 * Sets the timeSteps between each migration.
	 *
	 * @param migInterval - the timeSteps between each migration
	 */
	public void setMigInterval(int migInterval)
	{
		if (migInterval < 0) this.migInterval = 0;
		this.migInterval = migInterval;
	}

	/**
	 * This method processes the MigratingBuffalo state after each timeStep or
	 * turn. The Buffalo can consume upto 80 nutritional units based on the
	 * availability of food every 10 turns. Any amount of food can be left at
	 * this consumer's location, however, 10% of all nutrition will rot every
	 * turn.
	 *
	 * @param timeStep - the game time so far.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		if (timeStep.getValue() % migInterval == 0)
		super.process(timeStep);
		// 10% of food rots
		reduceStock((int) Math.round(getStock() * 0.1));
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
		return "Buffalo(" + getStock() + ")";
	}
}
