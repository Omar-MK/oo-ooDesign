/**
 * The CornFarmer class allows for the creation of Farmer Objects which
 * are a type of AbstractItem. CornFarmers produce 25 nutritional units every 4
 * game time steps or turns.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class CornFarmer extends Farmer
{
	/**
	 * Creates a CornFarmer Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the CornFarmer Object on
	 * @param xCoordinate - the x coordinate of this Farmer Object on the grid
	 * @param yCoordinate - the y coordinate of this Farmer Object on the grid
	 */
	public CornFarmer(Grid grid, int xCoordinate, int yCoordinate)
	{
		super(grid, xCoordinate, yCoordinate, 2, 1);
		productionCapacity = 25;
		setProductionInterval(4);
	}

	/**
	 * Returns a string about the nutrtion at this CornFarmer Object location on
	 * the grid.
	 * String takes the form: Corn(nutrition)
	 *
	 * @return the nutrtion at this CornFarmer Object location on the grid
	 */
	@Override
	public String toString()
	{
		return "Corn(" + getStock() + ")";
	}
}
