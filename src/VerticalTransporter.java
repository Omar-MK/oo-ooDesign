/**
 * The VerticalTransporter class allows for the creation of Transporter
 * Objects which are a type of AbstractItem. VerticalTransporters transport
 * nutrition from Farmer Objecs to Consumer Objects if they are on the same
 * column and the Transporter Objects exists between them.
 *
 * @author 170018405
 * @version Oct 19, 2018
 */
public class VerticalTransporter extends Transporter
{
	/**
	 * Creates a VerticalTransporter Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the VerticalTransporter Object on
	 * @param xCoordinate - the x coordinate of this Transporter Object
	 * @param yCoordinate - the y coordinate of this Transporter Object
	 * @param carryCap - the max nutrition that can be carried by this
	 * Transporter at each timeStep or turn.
	 */
	public VerticalTransporter(Grid grid, int xCoordinate, int yCoordinate,
	 int carryCap)
	{
		super(grid, xCoordinate, yCoordinate, carryCap);
	}

	/**
	 * Searches for the closest Farmer Object north and south of its location on
	 * the grid.
	 *
	 * @return a reference to the Farmer Object found if there are no obstacles.
	 * Null otherwise.
	 */
	@Override
	public Farmer setFarmer()
	{
		AbstractItem item;
		// find first AbstractItem north
		for (int i = yCoordinate - 1; i > -1; i--)
		{
			item = grid.getItem(xCoordinate, i);
			if (item != null)
			{
				if (item instanceof Farmer) return (Farmer) item;
				// else, there is a blockage, so stop searching north
				break;
			}
		}
		// find first AbstractItem south
		for (int i = yCoordinate + 1; i < grid.getHeight(); i++)
		{
			item = grid.getItem(xCoordinate, i);
			if (item != null)
			{
				if (item instanceof Farmer) return (Farmer) item;
				// else, there is a blockage, so stop searching south
				break;
			}
		}
		// no Farmer Objects north or south
		return null;
	}

	/**
	 * Searches for the closest Consumer Object north and south of its location
	 * on the grid.
	 *
	 * @return a reference to the Consumer Object found if there are no
	 * obstacles. Null otherwise.
	 */
	@Override
	public Consumer setConsumer()
	{
		AbstractItem item;
		// find first AbstractItem north
		for (int i = yCoordinate - 1; i > -1; i--)
		{
			item = grid.getItem(xCoordinate, i);
			if (item != null)
			{
				if (item instanceof Consumer) return (Consumer) item;
				// else, there is a blockage, so stop searching north
				break;
			}
		}
		// find first AbstractItem south
		for (int i = yCoordinate + 1; i < grid.getHeight(); i++)
		{
			item = grid.getItem(xCoordinate, i);
			if (item != null)
			{
				if (item instanceof Consumer) return (Consumer) item;
				// else, there is a blockage, so stop searching south
				break;
			}
		}
		// no Consumer Objects north or south
		return null;
	}

	/**
	 * Returns a string VT
	 *
	 * @return "VT"
	 */
	@Override
	public String toString()
	{
		return "VT";
	}
}
