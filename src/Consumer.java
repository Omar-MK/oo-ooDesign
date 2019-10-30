/**
 * The Consumer class is an abstract class which extends the AbstractItem class
 * and provides shared functionality between different consumer types.
 * @author 170018405
 * @version Oct 19, 2018
 */

abstract class Consumer extends AbstractItem
{
	/**
	 * The maximum consumption in units of nutrition a consumer can eat.
	 */
	protected int consumption;

	/** Creates a Consumer Object and registers this object on the grid passed
	 * as a parameter.
	 *
	 * @param grid - the grid on which this Consumer Object should be registered
 	 * @param yCoordinate - the y coordinate of this Consumer Object on the grid
	 * @param xCoordinate - the x coordinate of this Consumer Object on the grid
	 * nutritional units
	 */
	protected Consumer(Grid grid, int yCoordinate, int xCoordinate)
	{
		this.grid = grid;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	/**
	 * Retuns the consumption of nutrition per timeStep of the consumer.
	 *
	 * @return consumption of nutrition per timeStep of the consumer
	 */
	public int getConsumption()
	{
		return consumption;
	}

	/**
	 * Sets the consumption of nutrition per timeStep of the consumer.
	 *
	 * @param consumption - the nutrition per timeStep the consumer consumes
	 */
	public void setConsumption(int consumption)
	{
		/* negative consumption values do not increase food as grid
		 reduceStockAt method checks for this. */
		this.consumption = consumption;
	}

	/**
	 * This method processes the Consumer Object state for a given timeStep.
	 * In this implementation, the method causes a consumer to consume
	 * nutrition based on the consumption capacity and availability of food. In
	 * this basic implementation, consumers consume every turn and excess food
	 * carried forward to the next.
	 *
	 * @param timeStep - the game time so far.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		/* currentStock is saved as the getStock method checks coordinates
		 everytime it's called. This saves an extra computation. */
		int currentStock = getStock();
		int consumed;
		// when the food reserve is larger than consumption cap
		if (currentStock >= consumption)
		{
			consumed = consumption;
			reduceStock(consumption);
		}
		// otherwise
		else
		{
			consumed = currentStock;
			grid.emptyStockAt(xCoordinate, yCoordinate);
		}
		grid.recordConsumption(consumed);
	}

	/**
	 * Returns the stock at this consumer's location.
	 *
	 * @return the stock at this consumer's location
	 */
	@Override
	protected int getStock()
	{
		return grid.getStockAt(xCoordinate, yCoordinate);
	}

	/**
	 * Adds to the stock at this consumer's location.
	 *
	 * @param nutrition - the nutrition to add to this consumer's location
	 */
	@Override
	protected void addToStock(int nutrition)
	{
		grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
	}

	/**
	 * Decreases the stock at this consumer's location.
	 *
	 * @param nutrition - the nutrition to remove from this consumer's location
	 */
	@Override
	protected void reduceStock(int nutrition)
	{
		grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
	}

	/**
	 * Checks if an AbstractItem Object shares the same coordinates as this
	 * Consumer Object.
	 *
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
}
