/**
 * The HorizontalTransporter class allows for the creation of Transporter
 * Objects which are a type of AbstractItem. HorizontalTransporters transport
 * nutrition from Farmer Objecs to Consumer Objects if they are on the same row
 * and the Transporter Objects exists between them.
 *
 * @author 170018405
 * @version Oct 19, 2018
 */
public class HorizontalTransporter extends Transporter
{
	/**
	 * Creates a HorizontalTransporter Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the HorizontalTransporter Object on
	 * @param xCoordinate - the x coordinate of this Transporter Object
	 * @param yCoordinate - the y coordinate of this Transporter Object
	 * @param carryCap - the max nutrition that can be carried by this
	 * Transporter at each timeStep or turn.
	 */
	public HorizontalTransporter(Grid grid, int xCoordinate, int yCoordinate,
	 int carryCap)
	{
		super(grid, xCoordinate, yCoordinate, carryCap);
	}

	/**
	 * Searches for the closest Farmer Object east and west of its location on
	 * the grid.
	 *
	 * @return a reference to the Farmer Object found if there are no obstacles.
	 * Null otherwise.
	 */
	@Override
	public Farmer setFarmer()
	{
		AbstractItem item;
		// find first AbstractItem west
		for (int i = xCoordinate - 1; i > -1; i--)
		{
			item = grid.getItem(i, yCoordinate);
			if (item != null)
			{
				if (item instanceof Farmer) return (Farmer) item;
				// else, there is a blockage, so stop searching west
				break;
			}
		}
		// find first AbstractItem east
		for (int i = xCoordinate + 1; i < grid.getWidth(); i++)
		{
			item = grid.getItem(i, yCoordinate);
			if (item != null)
			{
				if (item instanceof Farmer) return (Farmer) item;
				// else, there is a blockage, so stop searching east
				break;
			}
		}
		// no Farmer Objects east or east
		return null;
	}

	/**
	 * Searches for the closest Consumer Object east and west of its location on
	 * the grid.
	 *
	 * @return a reference to the Consumer Object found if there are no
	 * obstacles. Null otherwise.
	 */
	@Override
	public Consumer setConsumer()
	{
		AbstractItem item;
		// find first AbstractItem west
		for (int i = xCoordinate - 1; i > -1; i--)
		{
			item = grid.getItem(i, yCoordinate);
			if (item != null)
			{
				if (item instanceof Consumer) return (Consumer) item;
				// else, there is a blockage, so stop searching west
				break;
			}
		}
		// find first AbstractItem east
		for (int i = xCoordinate + 1; i < grid.getWidth(); i++)
		{
			item = grid.getItem(i, yCoordinate);
			if (item != null)
			{
				if (item instanceof Consumer) return (Consumer) item;
				// else, there is a blockage, so stop searching east
				break;
			}
		}
		// no Consumer Objects east or east
		return null;
	}

	/**
	 * Returns a string HT
	 *
	 * @return "HT"
	 */
	@Override
	public String toString()
	{
		return "HT";
	}
}
