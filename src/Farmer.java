/**
 * The Farmer class is an abstract class which extends the AbstractItem class
 * and provides shared functionality between different Farmer types.
 * @author 170018405
 * @version Oct 19, 2018
 */
abstract class Farmer extends AbstractItem
{
	/**
	 * The maximum produce in units of nutrition a farmer can produce.
	 */
	protected int productionCapacity;

	/**
	 * The interval (wait time in unit timeStep) between each back of produce.
	 */
	private int productionInterval; // private - cannot be set to below 0

	/**
	 * The vertical space required to produce.
	 */
	private int vSpaceReq; // private - cannot be set to below 0

	/**
	 * The horizontal space required to produce.
	 */
	private int hSpaceReq; // private - cannot be set to below 0

	/**
	 * A variable used to keep track if a farmer has enough space to produce at
	 * a given turn. This variable is updated via the checkSpace method.
	 */
	protected boolean hasSpace;

	/** Creates a Farmer Object and registers this object on the grid passed as
	 * a parameter.
	 *
	 * @param grid - the grid on which this Farmer Object should be registered
 	 * @param yCoordinate - the y coordinate of this Farmer Object on the grid
	 * @param xCoordinate - the x coordinate of this Farmer Object on the grid
	 * @param hSpaceReq - the horizontal space in grid units required to produce
	 * @param vSpaceReq - the vertical space in grid units required to produce
	 */
	protected Farmer(Grid grid, int yCoordinate, int xCoordinate, int hSpaceReq, int vSpaceReq)
	{
		this.grid = grid;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.productionCapacity = productionCapacity;
		setProductionInterval(productionInterval);
		setVSpace(vSpaceReq);
		setHSpace(hSpaceReq);
		/* The registerItem function uses the checkSpace to update the hasSpace
		 field which inturn causes the Farmer object to produce nutriton or not.
		 */
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	/**
	 * Returns the vertical space required to produce.
	 *
	 * @return vertical space required to produce
	 */
	public int getVSpaceReq()
	{
		return vSpaceReq;
	}

	/**
	 * Returns the horizontal space required to produce.
	 *
	 * @return horizontal space required to produce
	 */
	public int getHSpaceReq()
	{
		return hSpaceReq;
	}

	/**
	 * Returns the interval between each production cycle in unit time steps.
	 *
	 * @return the interval between each production cycle in unit time steps
	 */
	public int getProductionInterval()
	{
		return productionInterval;
	}

	/**
	 * Sets the vertical space required to produce.
	 *
	 * @param verticalSpaceRequired the vertical space required to produce
	 */
	public void setVSpace(int verticalSpaceRequired)
	{
		if (verticalSpaceRequired > 0) vSpaceReq = verticalSpaceRequired;
	}

	/**
	 * Sets the horizontal space required to produce.
	 *
	 * @param horizontalSpaceRequired the horizontal space required to produce
	 */
	public void setHSpace(int horizontalSpaceRequired)
	{
		if (horizontalSpaceRequired > 0) hSpaceReq = horizontalSpaceRequired;
	}

	/**
	 * Sets the production interval between production cycles.
	 *
	 * @param productionInterval the production interval between
	 * production cycles
	 */
	public void setProductionInterval(int productionInterval)
	{
		if (productionInterval > 0)
		this.productionInterval = productionInterval;
	}

	/**
	 * Checks the farmer has enough space to produce crops. If a Farmers Object
	 * is within the vSpaceReq and hSpaceReq proximity, the this method will
	 * return false indicating that this Farmer Object has no space to produce.
	 * As well as returning the result, this method also updates the production
	 * status of this Object.
	 *
	 * @return true if farmer can produce and false otherwise
	 */
	public boolean checkSpace()
	{
		// check horizontal space before
		for (int i = xCoordinate - 1; i > xCoordinate - hSpaceReq - 1; i--)
		{
			if (i < 0) break; // out-of-bounds so break
			else if (grid.getItem(i, yCoordinate) instanceof Farmer)
			{
				hasSpace = false;
				return hasSpace;
			}

		}
		// check horizontal space after
		for (int i = xCoordinate + 1; i < xCoordinate + hSpaceReq + 1; i++)
		{
			if (i > grid.getWidth() - 1) break; // out-of-bounds so break
			else if (grid.getItem(i, yCoordinate) instanceof Farmer)
			{
				hasSpace = false;
				return hasSpace;
			}
		}
		// check above
		for (int i = yCoordinate - 1; i > yCoordinate - hSpaceReq - 1; i--)
		{
			if (i < 0) break; // out-of-bounds so break
			else if (grid.getItem(xCoordinate, i) instanceof Farmer)
			{
				hasSpace = false;
				return hasSpace;
			}
		}
		// check below
		for (int i = yCoordinate + 1; i < yCoordinate + vSpaceReq + 1; i++)
		{
			if (i > grid.getHeight() - 1) break; // out-of-bounds so break
			else if (grid.getItem(xCoordinate, i) instanceof Farmer)
			{
				hasSpace = false;
				return hasSpace;
			}
		}
		// no blockage found
		hasSpace = true;
		return hasSpace;
	}

	/**
	 * This method processes the Farmer state at each timeStep. Updates the
	 * nutrition value at this farmer location. In this basic implementation,
	 * process checks if enough time has passed for the produce to be produced,
	 * and updates the stock field accordingly given the farmer has enough
	 * space to produce. Full production capacity is produced at the set time
	 * interval (no negative or positive effects). Negative or positive effects
	 * (e.g. pests or use of fertiliser) can be implemented in a sub-class by
	 * using the setProductionInterval method or directly accessing the
	 * productionCapacity field to change farming performance.
	 *
	 * @param timeStep - the game time so far.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		if (timeStep.getValue() % productionInterval == 0 && hasSpace)
		{
			grid.addToStockAt(xCoordinate, yCoordinate, productionCapacity);
			grid.recordProduction(productionCapacity);
		}
	}

	/**
	 * Returns the stock at this farmer's location.
	 *
	 * @return the stock at this farmer's location
	 */
	@Override
	public int getStock()
	{
		return grid.getStockAt(xCoordinate, yCoordinate);
	}

	/**
	 * Checks if an AbstractItem Object shares the same coordinates as this
	 * Farmer Object.
	 *
	 * @param o - the Object to equate to
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

	/**
	 * Adds to the stock at this farmer's location.
	 *
	 * @param nutrition - the nutrition to add to this farmer's location
	 */
	@Override
	protected void addToStock(int nutrition)
	{
		grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
	}

	/**
	 * Decreases the stock at this farmer's location.
	 *
	 * @param nutrition - the nutrition to remove from this farmer's location
	 */
	@Override
	protected void reduceStock(int nutrition)
	{
		grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
	}
}
