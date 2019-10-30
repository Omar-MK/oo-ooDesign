public class MigratingBuffaloTest
{

    public static void main(String[] args)
    {

        Grid grid = new Grid(5, 5);
        new CornFarmer(grid, 0, 0);
        new HorizontalTransporter(grid, 0, 2, 10);
        new MigratingBuffalo(grid, 0, 4);

        TimeStep t = new TimeStep();

        Game game = new Game(grid);
        game.run(10);

        // calculation of game state
        // after 10 turns 50 nutritional units should have been produced by the farmer
        // 10 units are transported per turn, :- 10, then 10, then 5 and this happens twice within 10 periods
        // at turn 4, first full-units are transported. 10% of total rot left-over 9
        // at turn 5, second full-units are transported, 10% of total rot (9 + 10) * 0.9 = 17
        // at turn 6, 5 units transported, 10% of total rot (17 + 5) * 0.9 = 20
        // at turn 7, 20 * 0.9 = 18
        // turn 8 new production. (18 + 10) * 0.9 = 25
        // turn 9, (25 + 10) * 0.9 = 31
        // turn 10, (31 + 5) = 36 which is consumed

        if (grid.getTotalProduction() == 50 && grid.getTotalConsumption() == 36)
        System.out.println("Pass");
        else
        System.out.println("Fail");
    }
}
