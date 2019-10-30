/**
 * The Transporter class is an abstract class which extends the AbstractItem
 * class and provides shared functionality between different Transporter types,
 * as well as provides abstract methods to capture the required functionality
 * for sub-classes.
 * @author 170018405
 * @version Oct 19, 2018
 */
abstract class Transporter extends AbstractItem implements
 Comparable<Transporter>
{
	/**
	 * The Farmer Object to remove the stock from.
	 */
	private Farmer connectedFarmer;

	/**
	 * The Consumer Object to deliver the stock to.
	 */
	private Consumer connectedConsumer;

	/**
	 * The carriage capacity of the transporter per turn.
	 */
	private int carryCap;

	/**
	 * Creates a Transporter Object and places it on the passed grid.
	 *
	 * @param grid - the grid to place the Transporter Object on
	 * @param xCoordinate - the x coordinate of this Transporter Object
	 * @param yCoordinate - the y coordinate of this Transporter Object
	 * @param carryCap - the max nutrition that can be carried by this
	 * Transporter at each timeStep or turn.
	 */
	protected Transporter(Grid grid, int yCoordinate, int xCoordinate,
	 int carryCap)
	{
		this.grid = grid;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		if (carryCap > 0) this.carryCap = carryCap;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

    /**
     * Sets the connectedFarmer and connectedConsumer fields by calling the
     * setFarmer and setConsumer methods.
     */
    public void updateRoute()
    {
        connectedFarmer = setFarmer();
        connectedConsumer = setConsumer();
    }

	/**
	 * Abstract method to capture the functionality of finding and setting the
	 * connected farmer from where the nutrition will be transported.
	 */
	protected abstract Farmer setFarmer();

	/**
	 * Abstract method to capture the functionality of finding and setting the
	 * connected consumer where the nutrition will be delivered.
	 */
	protected abstract Consumer setConsumer();

	/**
	 * Processes the Transporter state.
	 * Moves produce from farmer to consumer if a farmer Object is attached to
	 * connectedFarmer and a consumer Object is attached to connectedConsumer -
	 * i.e. a valid route has been established
	 *
	 * @param timeStep - the game time so far.
	 */
	@Override
	public void process(TimeStep timeStep)
	{
		if (routeEstablished())
		{
			/* could ofcourse use getStock, addToStock, and reduceStock methods
			 of this class. But since the routeEstablished is checked, why use
			 more if statements when they are not needed. The methods mentioned
			  are usefull if a subclass intends to use these functionalities in
			   a different way. */
			int farmerStock = connectedFarmer.getStock();
			if (farmerStock > carryCap)
			{
				connectedFarmer.reduceStock(carryCap);
				connectedConsumer.addToStock(carryCap);
			}
			else
			{
				connectedFarmer.reduceStock(farmerStock);
				connectedConsumer.addToStock(farmerStock);
			}
		}
	}

	/**
	 * Checks if an AbstractItem Object shares the same coordinates as this
	 * Transporter Object.
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

	/**
	 * Compares the coordinates of this Transporter Object with the Transporter
	 * Object passed in. If this Transporter Object has a lower y coordinate,
	 * then a value of 1 is returned. If both Objects share the same y
	 * coordinate and this Object has a lower x coordinate, then a value of 1
	 * is returned. A value of 0 is returned if both Objects share the same
	 * coordinates and -1 otherwise.
	 *
	 * @return 1 if this Object has a lower y, or y and x (if y is equal). 0 if
	 * both Objects share the same coordinates, and -1 otherwise.
	 */
	@Override
	public int compareTo(Transporter o)
	{
		if (yCoordinate < o.yCoordinate || yCoordinate == o.yCoordinate &&
		 xCoordinate < o.xCoordinate) return -1;
		else if (yCoordinate == o.yCoordinate && xCoordinate == o.xCoordinate)
		return 0;
		else return 1;
	}

	/**
	 * Returns the stock at this transporter's connected farmer.
	 * If no farmer is connected, a value of 0 is returned.
	 *
	 * @return the stock at this transporter's connected farmer
	 */
	@Override
	protected int getStock()
	{
		if (connectedFarmer != null) return connectedFarmer.getStock();
		return 0;
	}

	/**
	 * Adds to the stock to this transporter's connected consumer.
	 *
	 * @param nutrition - the nutrition to add to the connected consumer
	 */
	@Override
	protected void addToStock(int nutrition)
	{
		if (connectedConsumer != null) connectedConsumer.addToStock(nutrition);
	}

	/**
	 * Decreases the stock at this consumer's connected farmer.
	 *
	 * @param nutrition - the nutrition to remove from the connected farmer
	 */
	@Override
	protected void reduceStock(int nutrition)
	{
		if (connectedFarmer != null) connectedFarmer.reduceStock(nutrition);
	}

	/**
	 * Checks if a valid route has been established.
	 *
	 * @return true if a complete route has been established and false otherwise
	 */
	private boolean routeEstablished()
	{
		return (connectedFarmer != null && connectedConsumer != null);
	}
}
