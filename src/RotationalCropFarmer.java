/**
 * The RotationalCropFarmer class allows for the creation of Farmer Objects
 * which are a type of AbstractItem. RotationalCropFarmers rotate crops between
 * corn, radish, and potato, producing 25, 10, and 40 nutritional units
 * respectively. It takes 4 turns to produce a corn crop, 3 to produce a radish
 * crop, and 6 to produce a potato crop.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class RotationalCropFarmer extends Farmer
{
	/**
	 * The production capacity data.
	 */
	private final int[] productionCaps = {25, 10, 40};

	/**
	 * The production interval data.
	 */
	private final int[] productionIntervals = {4, 3, 6};

	/**
	 * The crop names.
	 */
	private final String[] crop = {"Corn", "Radish", "Potato"};

	/**
	 * The crop rotation number 0, 1, or 2.
	 */
	private int cropNum;

	/**
	 * Creates a RotationalCropFarmer Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the RotationalCropFarmer Object on
	 * @param xCoordinate - the x coordinate of this Farmer Object on the grid
	 * @param yCoordinate - the y coordinate of this Farmer Object on the grid
	 */
	public RotationalCropFarmer(Grid grid, int xCoordinate, int yCoordinate)
	{
		super(grid, xCoordinate, yCoordinate, 2, 2);
		productionCapacity = 25;
		setProductionInterval(4);
	}

	/**
	 * Updates the crop rotation data and calls the super-class process method.
	 *
	 * @param timeStep - the game time so far.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		super.process(timeStep);

		if (timeStep.getValue() % productionIntervals[cropNum] == 0)
		{
			cropNum++;
			if (cropNum > 2) cropNum = 0;
			productionCapacity = productionCaps[cropNum];
			setProductionInterval(productionIntervals[cropNum]);
		}
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
		return crop[cropNum] + "(" + getStock() + ")";
	}
}
