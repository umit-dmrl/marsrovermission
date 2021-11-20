import org.apache.commons.collections4.ListUtils;

import java.util.Arrays;
import java.util.List;

public class RoverMissionApplication {
    public static void main(String[] args) {
        String roverInputs = new StringBuilder("5 5\n")
                .append("1 2 N\n")
                .append("LMLMLMLMM\n")
                .append("3 3 E\n")
                .append("MMRMMRMRRM\n")
                .toString();

        String[] inputRows = roverInputs.split("\n");

        String rectangleCoordinates = inputRows[0];

        RectangleInputValidator rectangleInputValidator = new RectangleInputValidator();
        if (!rectangleInputValidator.validate(rectangleCoordinates)) {
            System.out.println("Rectangle input coordinates is not valid! Please check your inputs");
            return;
        }

        List<List<String>> roverInfoList = ListUtils.partition(Arrays.asList(inputRows).subList(1, inputRows.length), 2);

        roverInfoList.stream().forEach(roverInfo -> {
            String roverFirstPosition = roverInfo.get(0);
            String roverInstructions = roverInfo.get(1);

            RoverAction roverAction = new RoverAction(rectangleCoordinates, roverFirstPosition, roverInstructions);
            roverAction.start();
        });
    }
}
