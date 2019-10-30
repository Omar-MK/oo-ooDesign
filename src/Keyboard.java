import java.util.*;

/** This class contains a method used to get user input from the command line.
 * @author 170018405
 * @version Oct 12, 2018
 */
public class Keyboard
{
	/**
	 * Retrieves an integer from the command-line.
	 *
	 * @return integer typed at command-line
	 */
	public static int getInt()
	{
		while (true)
		{
			Scanner input = new Scanner(System.in);
			if (input.hasNextInt()) return input.nextInt();
			else System.out.println("Please enter an integer");
		}
	}
}
