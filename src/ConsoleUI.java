import java.util.Arrays;

/** This class contains the main method and its helper methods which provide a
 * command-line UI to operate the Farming-sim game.
 * @author 170018405
 * @version Oct 19, 2018
 */
public class ConsoleUI
{
	public static void main(String[] args)
	{
		// check 2 command arguments are provided
		checkCommandArgNum(args);

		System.out.println("\n*** This is a console UI for the Farming-sim game ***");

		// create grid
		Grid grid;
		grid = new Grid(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

		// offer menu
		int optionChoice;
		TimeStep t = new TimeStep();
		do
		{
			displayMainMenu();
			// get choice
			optionChoice = Keyboard.getInt();
			System.out.println();

			// process menu options
			switch (optionChoice)
			{
				case 1: insertFarmer(grid);
				break;
				case 2: insertTransporter(grid);
				break;
				case 3: insertConsumer(grid);
				break;
				case 4: showGrid(grid);
				break;
				case 5: endGameTurn(grid, t);
				break;
				case 6: removeItem(grid);
				break;
				case 7: break;
				default: System.out.println("Invalid choice, retry");
			}
		} while (optionChoice != 7);
		System.out.println("Goodbye! :)");
	}

	/** This method checks the correct number of command line arguments is
	* given, and that both command line arguments are integers greater than 0.
	* The method will print an error message if unexpected command line
	* arguments are provided, then it will force the program to exit.
	* @param args the args string array (the main method parameter)
	* @param errMessage the message to print to the command line before exiting
	*/
	static void checkCommandArgNum(String[] args)
	{
		String err = "Please provide two Integers as command-line arguments"
		 + " representing the Height and Width of the grid.";
		if (args.length != 2)
		{
			System.out.println("Expected " + 2
			+ " command line arguments, but got " + args.length + ".");
			System.out.println(err);
			System.exit(0);
		}
		try
		{
			int width = Integer.parseInt(args[0]);
			int height = Integer.parseInt(args[1]);
			if (width < 1 || height < 1)
			{
				System.out.println("The Integers provided must be greater than 0");
				System.exit(0);
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println(err);
			System.exit(0);
		}
	}

	static void insertFarmer(Grid grid)
	{
		// get coordinate
		int[] coordinate = getCoordinate(grid);
		// display options
		displayFarmerMenu();
		// get choice
		int optionChoice = getChoice(1, 4);
		System.out.println();

		// process menu options
		switch (optionChoice)
		{
			case 1: new CornFarmer(grid, coordinate[1], coordinate[0]);
			break;
			case 2: new RadishFarmer(grid, coordinate[1], coordinate[0]);
			break;
			case 3: new RotationalCropFarmer(grid, coordinate[1],
			 coordinate[0]);
			break;
			case 4: break;
			default: System.out.println("Invalid choice, retry");
		}
	}

	static void insertTransporter(Grid grid)
	{
		// get coordinate
		int[] coordinate = getCoordinate(grid);
		// display options
		displayTransporterMenu();
		// get choice
		int optionChoice = getChoice(1, 4);
		System.out.println();

		// process menu options
		switch (optionChoice)
		{
			case 1: new HorizontalTransporter(grid, coordinate[1],
			 coordinate[0], 10);
			break;
			case 2: new VerticalTransporter(grid, coordinate[1], coordinate[0],
			 10);
			break;
			case 3: new NearestTransporter(grid, coordinate[1], coordinate[0],
			 10);
			break;
			case 4: break;
			default: System.out.println("Invalid choice, retry");
		}
	}

	static void insertConsumer(Grid grid)
	{
		// get coordinate
		int[] coordinate = getCoordinate(grid);
		// display options
		displayConsumerMenu();
		// get choice
		int optionChoice = getChoice(1, 4);
		System.out.println();

		// process menu options
		switch (optionChoice)
		{
			case 1: new Rabbit(grid, coordinate[1], coordinate[0]);
			break;
			case 2: new Beaver(grid, coordinate[1], coordinate[0]);
			break;
			case 3: new MigratingBuffalo(grid, coordinate[1], coordinate[0]);
			break;
			case 4: break;
			default: System.out.println("Invalid choice, retry");
		}
	}

	static void showGrid(Grid grid)
	{
		grid.display();
	}

	static void endGameTurn(Grid grid, TimeStep t)
	{
		// code from Game class to proceed the game 1 step
		grid.processItems(t);
		grid.display();

		System.out.println("Time step: " + t.getValue());
		System.out.println("Total production: " + grid.getTotalProduction());
		System.out.println("Total consumption: " + grid.getTotalConsumption());
		if (grid.getTotalProduction() > 0) {
			System.out.println("Score: " + String.format("%.2f", (double) grid.getTotalConsumption() / grid.getTotalProduction()));
		} else {
			System.out.println("Score: NA");
		}
		System.out.println();
		t.increment();
	}

	static void removeItem(Grid grid)
	{
		System.out.println("Enter the coordinate of the item to be removed: ");
		// get coordinate
		int[] coordinate = getCoordinate(grid);
		// remove item
		grid.removeItem(coordinate[0], coordinate[1]);
		System.out.println("Item removed!");
	}

	static void displayMainMenu()
	{
		System.out.println("\n1. Insert Farmer");
		System.out.println("2. Insert Transporter");
		System.out.println("3. Insert Consumer");
		System.out.println("4. Show grid");
		System.out.println("5. End turn");
		System.out.println("6. Remove Item");
		System.out.println("7. Exit Game\n");
		System.out.print("Enter choice [1-7]: ");
	}

	static void displayFarmerMenu()
	{
		System.out.println("\nChoose Farmer type");
		System.out.println("1. Corn Farmer");
		System.out.println("2. Radish Farmer");
		System.out.println("3. Rotational Crop Farmer");
		System.out.println("4. Go back\n");
		System.out.print("Enter choice [1-4]: ");
	}

	static void displayTransporterMenu()
	{
		System.out.println("\nChoose Transporter type");
		System.out.println("1. Horizontal Transporter");
		System.out.println("2. Vertical Transporter");
		System.out.println("3. Nearest Transporter");
		System.out.println("4. Go back");
	}

	static void displayConsumerMenu()
	{
		System.out.println("\nChoose Consumer type");
		System.out.println("1. Rabbit");
		System.out.println("2. Beaver");
		System.out.println("3. Migrating Buffalo");
		System.out.println("4. Go back");
	}

	private static int[] getCoordinate(Grid grid)
	{
		int[] coordinate = new int[2];
		System.out.print("Enter x coordinate: ");
		coordinate[0] = getChoice(1, grid.getWidth()) - 1;
		System.out.print("Enter y coordinate: ");
		coordinate[1] = getChoice(1, grid.getHeight()) - 1;
		return coordinate;
	}

	private static int getChoice(int min, int max)
	{
		int choice = 0;
		do
		{
			System.out.print("\nEnter a number between " + min + "-" + max
			 + ": ");
			choice = Keyboard.getInt();
		} while (choice < min || choice > max);
		return choice;
	}
}
