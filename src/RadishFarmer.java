/**
 * The RadishFarmer class allows for the creation of Farmer Objects which
 * are a type of AbstractItem. RadishFarmers produce 10 nutritional units every
 * 3 game time steps or turns.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class RadishFarmer extends Farmer
{
	/**
	 * Creates a RadishFarmer Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the RadishFarmer Object on
	 * @param xCoordinate - the x coordinate of this Farmer Object on the grid
	 * @param yCoordinate - the y coordinate of this Farmer Object on the grid
	 */
	public RadishFarmer(Grid grid, int xCoordinate, int yCoordinate)
	{
		super(grid, xCoordinate, yCoordinate, 1, 1);
		productionCapacity = 10;
		setProductionInterval(3);
	}

	/**
	 * Returns a string about the nutrtion at this RadishFarmer Object location
	 * on the grid.
	 * String takes the form: Radish(nutrition)
	 *
	 * @return the nutrtion at this RadishFarmer Object location on the grid
	 */
	@Override
	public String toString()
	{
		return "Radish(" + getStock() + ")";
	}
}
