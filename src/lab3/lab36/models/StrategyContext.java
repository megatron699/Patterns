package lab3.lab36.models;

import lab3.lab36.interfaces.Strategy;

public class StrategyContext {
    private Strategy studentCheckStrategy;

    public void setStudentCheckStrategy(Strategy studentCheckStrategy) {
        this.studentCheckStrategy = studentCheckStrategy;
    }

    public void executeStrategy(String inputFileName, String outputFileName) throws Exception {
        studentCheckStrategy.checkAverageStudent(inputFileName, outputFileName);
    }

}
