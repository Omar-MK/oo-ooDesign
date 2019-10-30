import java.lang.Math;

/**
 * The NearestTransporter class allows for the creation of Transporter
 * Objects which are a type of AbstractItem. NearestTransporters transport
 * nutrition from the nearest Farmer Objec to the nearest Consumer Object as
 * long as 2 Consumer or Farmer Objects are not equidistant from this
 * Transporter Object.
 *
 * @author 170018405
 * @version Oct 19, 2018
 */
public class NearestTransporter extends Transporter
{
	/**
	 * Creates a NearestTransporter Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the NearestTransporter Object on
	 * @param xCoordinate - the x coordinate of this Transporter Object
	 * @param yCoordinate - the y coordinate of this Transporter Object
	 * @param carryCap - the max nutrition that can be carried by this
	 * Transporter at each timeStep or turn.
	 */
	public NearestTransporter(Grid grid, int xCoordinate, int yCoordinate, int carryCap)
	{
		super(grid, xCoordinate, yCoordinate, carryCap);
	}

	/**
	 * Searches for the closest Farmer Object from its location on the grid.
	 *
	 * @return a reference to the Farmer Object found as long as two Farmer
	 * Objects are not equidistant from this Transporter. Null otherwise.
	 */
	@Override
	public Farmer setFarmer()
	{
		int x = -1 , y = -1, sameDistCount = 0;
		int minDist = Math.max(grid.getHeight(), grid.getWidth());
		// iterate through the whole grid
		for (int i = 0; i < grid.getWidth(); i++)
		{
			for (int j = 0; j < grid.getHeight(); j++)
			{
				if (grid.getItem(i, j) instanceof Farmer)
				{
					// calculate distance between grid[i][j] and this Farmer
					int tempDist = Math.max(Math.abs(i - xCoordinate), Math.abs(j - yCoordinate));
					if (tempDist < minDist)
					{
						minDist = tempDist;
						x = i;
						y = j;
						sameDistCount = 0;
					}
					else if (tempDist == minDist)
					{
						sameDistCount++;
					}
				}
			}
		}
		if (sameDistCount == 0 && x > -1 && y > -1) return (Farmer) grid.getItem(x, y);
		return null;
	}

	/**
	 * Searches for the closest Consumer Object from its location on the grid.
	 *
	 * @return a reference to the Consumer Object found as long as two Consumer
	 * Objects are not equidistant from this Transporter. Null otherwise.
	 */
	@Override
	public Consumer setConsumer()
	{
		int x = -1 , y = -1, sameDistCount = 0;
		int minDist = Math.max(grid.getHeight(), grid.getWidth());
		// iterate through the whole grid
		for (int i = 0; i < grid.getWidth(); i++)
		{
			for (int j = 0; j < grid.getHeight(); j++)
			{
				if (grid.getItem(i, j) instanceof Consumer)
				{
					// calculate distance between grid[i][j] and this Farmer
					int tempDist = Math.max(Math.abs(i - xCoordinate), Math.abs(j - yCoordinate));
					if (tempDist < minDist)
					{
						minDist = tempDist;
						x = i;
						y = j;
						sameDistCount = 0;
					}
					else if (tempDist == minDist)
					{
						sameDistCount++;
					}
				}
			}
		}
		if (sameDistCount == 0 && x > -1 && y > -1) return (Consumer) grid.getItem(x, y);
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
		return "NT";
	}
}
