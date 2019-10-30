import java.util.ArrayList;
import java.util.Collections;

/**
 * The Grid class extends the AbstractGrid class and provides methods to create
 * the grid Object, register/remove AbstractItems on the grid, update nutrition
 * across the grid, and process the items on the grid using the AbstractItem
 * Objects' process method.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class Grid extends AbstractGrid
{
	/**
	 * List of farmer objects on the grid
	 */
	private ArrayList<Farmer> farmerList;

	/**
	 * List of transporter objects on the grid
	 */
	private ArrayList<Transporter> transporterList;

	/**
	 * List of consumer objects on the grid
	 */
	private ArrayList<Consumer> consumerList;

	/**
	 * Total production tally
	 */
	int totalProduction;

	/**
	 * Total consumption tally
	 */
	int totalConsumption;

	/**
	 * Instantiates a new grid object.
	 *
	 * @param width - the width of the grid in squares
	 * @param height - the height of the grid in squares
	 */
	public Grid(int height, int width)
	{
		grid = new AbstractItem[height][width];
		stock = new int[height][width];
		farmerList = new ArrayList<Farmer>();
		transporterList = new ArrayList<Transporter>();
		consumerList = new ArrayList<Consumer>();
	}

	/**
     * Returns the width of the grid.
     *
     * @return The width of the grid
     */
	@Override
 	public int getWidth()
	{
		return grid[0].length;
	}

    /**
     * Returns the height of the grid.
     *
     * @return The height of the grid
     */
	@Override
    public int getHeight()
	{
		return grid.length;
	}

    /**
     * Registers an item on the grid at the specified location.
	 * Items that can be registered can be instances of Farmer, Consumer, or
	 * Transporter. This method will also update the Farmer and Transporter
	 * Object's production and transportation status.
     * Caution: this will overwrite whatever was at this location before!
 	 * If an invalid set of coordinates are provided, nothing is changed.
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param item
     */
	@Override
    public void registerItem(int xCoordinate, int yCoordinate,
	 AbstractItem item)
	{
		if (validCoordinate(xCoordinate, yCoordinate))
		{
			grid[yCoordinate][xCoordinate] = item;

			/* Add item into respective ArrayList to allow faster
			 processing of game state when processItems is called. */

			/* check if an AbstractItem Object exists in any of the lists with the
			 same coordinates as item. If this is the case, remove it. This is
			  neccessary since two lists could contain items with the same
			   coordinates. */
			if (farmerList.contains(item)) farmerList.remove(item);
			else if (consumerList.contains(item)) consumerList.remove(item);
			else if (transporterList.contains(item)) transporterList.remove(item);

			// add the item to the correct list
			if (item instanceof Farmer) farmerList.add((Farmer) item);
			else if (item instanceof Transporter)
			{
				transporterList.add((Transporter) item);
				// sort the transporters list
				Collections.sort(transporterList);
			}

			else if (item instanceof Consumer)
			consumerList.add((Consumer) item);

			// update routes
			for (int i = 0; i < transporterList.size(); i++)
			{
				transporterList.get(i).updateRoute();
			}
			// update farmer spaces
			for (int i = 0; i < farmerList.size(); i++)
			{
				farmerList.get(i).checkSpace();
			}
		}
	}

	/**
	 * Removes an item on the grid at the specified location.
	 * If an invalid set of coordinates are provided, nothing is changed.
	 * Updates Farmer production and Transporter route status.
	 *
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param item
	 */
	public void removeItem(int xCoordinate, int yCoordinate)
	{
		if (validCoordinate(xCoordinate, yCoordinate))
		{
			grid[yCoordinate][xCoordinate] = null;

			/* create can AbstractItem with the same coordinate to see if a
			 similar AbstractItem exists in any of the lists */
			AbstractItem item = new ComparisonItem(yCoordinate, xCoordinate);
			// Remove item from respective ArrayList
			if (farmerList.contains(item)) farmerList.remove(item);
			else if (transporterList.contains(item))
			transporterList.remove(item);
			else if (consumerList.contains(item))
			consumerList.remove(item);

			// update routes
			for (int i = 0; i < transporterList.size(); i++)
			{
				transporterList.get(i).updateRoute();
			}
			// update farmer spaces
			for (int i = 0; i < farmerList.size(); i++)
			{
				farmerList.get(i).checkSpace();
			}
		}
	 }

    /**
     * Return the item at row i and column j.
     * Returns null if there isn't an item at the specified location.
     * Also returns null for out-of-bounds queries.
     *
     * @param xCoordinate
     * @param yCoordinate
     * @return The Object (AbstractItem) that is stored at the specified grid
	 *  cell, null otherwise
     */
	@Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate)
	{
		if (!validCoordinate(xCoordinate, yCoordinate)) return null;
		return grid[yCoordinate][xCoordinate];
	}

    /**
     * Returns the stock (in units of nutrition at the specified location.
	 * Also return 0 for out-of-bounds queries (no stock outside play area).
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @return The stock (nutrition) at the specified location
     */
	@Override
    public int getStockAt(int xCoordinate, int yCoordinate)
	{
		if (!validCoordinate(xCoordinate, yCoordinate)) return 0;
		return stock[yCoordinate][xCoordinate];
	}

    /**
     * Clear the stock at the specified location. i.e. set it to 0.
     * This is equivalent to setStockAt(x,y,0).
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     */
	@Override
    public void emptyStockAt(int xCoordinate, int yCoordinate)
	{
		// setStockAt handles out-of-bound queries
		setStockAt(xCoordinate, yCoordinate, 0);
	}

    /**
     * Adds the specified amount to the stock value stored at the specified
	 * location.
	 * Does nothing if coordinates are out-of-bounds or if nutrition is negative
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
	@Override
	public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition)
	{
		if (validCoordinate(xCoordinate, yCoordinate) && nutrition > 0)
		stock[yCoordinate][xCoordinate] += nutrition;
	}

    /**
     * Reduces the stock at the specified location by the given amount or
	 * empties the stockpile depending on how much nutrition is available.
 	 * Does nothing if coordinates are out-of-bounds or nutrition is negative.
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
	@Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition)
	{
		if (validCoordinate(xCoordinate, yCoordinate) && nutrition > 0)
		{
			stock[yCoordinate][xCoordinate] -= nutrition;
			// stock cannot be negative, if stock < 0, reset to 0.
			if (stock[yCoordinate][xCoordinate] < 0)
			emptyStockAt(xCoordinate, yCoordinate);
		}
	}

    /**
     * Sets the stock value at a specified location.
	 * Does nothing if coordinates are out-of-bounds.
	 * This is the only method that allows a negative stock value to be placed
	 * on the grid. Causion should be exercised with this functionality.
     *
     * @param xCoordinate the row number
     * @param yCoordinate the column number
     * @param nutrition the amount
     */
	@Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition)
	{
		if(validCoordinate(xCoordinate, yCoordinate))
		stock[yCoordinate][xCoordinate] = nutrition;
	}

    /**
     * Processes all items on the grid. First all farmers, then all
	 * transporters, then all consumers.
     *
     * @param timeStep The time step we are at. This value may be used to
	 * determine production frequency of farmers.
     */
	@Override
    public void processItems(TimeStep timeStep)
	{
		/* using ArrayLists to track item locations makes it possible to update
		 items of certian types in sequence without needing to loop over every
		  grid position several times. */

		for (int i = 0; i < farmerList.size(); i++)
		{
			farmerList.get(i).process(timeStep);
		}
		for (int i = 0; i < transporterList.size(); i++)
		{
			transporterList.get(i).process(timeStep);
		}
		for (int i = 0; i < consumerList.size(); i++)
		{
			consumerList.get(i).process(timeStep);
		}
	}

    /**
     * Add to total production.
	 * Negative nutrition values do not reduce the totalProduction statistic.
     *
     * @param nutrition the amount
     */
	@Override
    public void recordProduction(int nutrition)
	{
		if (nutrition > 0) totalProduction += nutrition;
	}

    /**
     * Retrives the total production amount so far.
     *
     * @return total production
     */
	@Override
    public int getTotalProduction()
	{
		return totalProduction;
	}

    /**
     * Adds to total consumption.
     * Negative nutrition values do not reduce the totalConsumption statistic.
     *
     * @param nutrition the amount
     */
	@Override
    public void recordConsumption(int nutrition)
	{
		if (nutrition > 0) totalConsumption += nutrition;
	}

    /**
     * Retrieves the total consumption amount so far.
	 *
     * @return amount of consumption
     */
	@Override
    public int getTotalConsumption()
	{
		return totalConsumption;
	}

	/** Checks if the coordinate values passed are within bounds or not.
	 *
	 * @return true if coordinate values are within bounds and false otherwise

	 */
	private boolean validCoordinate(int xCoordinate, int yCoordinate)
	{
		return xCoordinate < getWidth() && xCoordinate > -1 &&
		 yCoordinate < getHeight() && yCoordinate > -1;
	}
}
