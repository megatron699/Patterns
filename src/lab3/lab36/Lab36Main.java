package lab3.lab36;

import lab3.lab36.models.SaxStrategy;
import lab3.lab36.models.StrategyContext;

public class Lab36Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Неверное число параметров");
            return;
        }

        StrategyContext strategyManager = new StrategyContext();
        //strategyManager.executeStrategy(Main.class.getClassLoader().getResource(args[0]).getPath(), args[1]);

        strategyManager.setStudentCheckStrategy(new SaxStrategy());
        strategyManager.executeStrategy(Lab36Main.
                class.
                getClassLoader()
                .getResource(args[0]).
                        getPath(),
                args[1]);
    }
}
