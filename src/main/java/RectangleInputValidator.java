public class RectangleInputValidator {

    public boolean validate(String rectangleCoordinates) {
        String[] rectangleCoordinate = rectangleCoordinates.split(" ");
        if (rectangleCoordinate.length < 2) {
            return false;
        }

        if (rectangleCoordinate[0].isEmpty() || rectangleCoordinate[1].isEmpty()) {
            return false;
        }

        try {
            Integer.valueOf(rectangleCoordinate[0]);
            Integer.valueOf(rectangleCoordinate[1]);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
