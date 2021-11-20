import java.util.Arrays;
import java.util.List;

public class RoverInputValidator {

    private static final List<String> HEADING_LIST = Arrays.asList("W", "E", "N", "S");
    private static final List<String> ACTION_LIST = Arrays.asList("L", "R", "M");

    public boolean validate(String roverFirstPosition, String roverInstructions, int rectangleCoordinateX, int rectangleCoordinateY) {
        if (!isValidRoverPosition(roverFirstPosition, rectangleCoordinateX, rectangleCoordinateY) || !isValidRoverInstructions(roverInstructions)) {
            return false;
        }
        return true;
    }

    private boolean isValidRoverPosition(String roverFirstPosition, int rectangleCoordinateX, int rectangleCoordinateY) {
        if (roverFirstPosition.isEmpty()) {
            return false;
        }

        String[] coordinates = roverFirstPosition.split(" ");

        if (coordinates.length < 3) {
            return false;
        }

        try {
            Integer.parseInt(coordinates[0]);
            Integer.parseInt(coordinates[1]);
        } catch (Exception e) {
            return false;
        }

        if (coordinates[2].isEmpty() || !HEADING_LIST.contains(coordinates[2])) {
            return false;
        }

        if (Integer.parseInt(coordinates[0]) < 0 || Integer.parseInt(coordinates[0]) > rectangleCoordinateX) {
            return false;
        }

        if (Integer.parseInt(coordinates[1]) < 0 || Integer.parseInt(coordinates[1]) > rectangleCoordinateY) {
            return false;
        }

        return true;
    }

    private boolean isValidRoverInstructions(String roverInstructions) {
        if (roverInstructions.isEmpty()) {
            return false;
        }

        for (String instruction : roverInstructions.split("")) {
            if (!ACTION_LIST.contains(instruction)) {
                return false;
            }
        }

        return true;
    }
}
