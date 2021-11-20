import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RoverAction {

    private final Map<String, String> leftRotationMap = new HashMap<>();
    private final Map<String, String> rightRotationMap = new HashMap<>();

    private String roverFirstPosition;
    private String roverInstructions;

    private int rectangleCoordinateX;
    private int rectangleCoordinateY;

    public RoverAction(String rectangleCoordinates, String roverFirstPosition, String roverInstructions) {
        this.roverFirstPosition = roverFirstPosition;
        this.roverInstructions = roverInstructions;

        //left rotation map
        this.leftRotationMap.put("N", "W");
        this.leftRotationMap.put("W", "S");
        this.leftRotationMap.put("S", "E");
        this.leftRotationMap.put("E", "N");

        //right rotation map
        this.rightRotationMap.put("N", "E");
        this.rightRotationMap.put("E", "S");
        this.rightRotationMap.put("S", "W");
        this.rightRotationMap.put("W", "N");

        String[] rectangleCoordinate = rectangleCoordinates.split(" ");
        this.rectangleCoordinateX = Integer.valueOf(rectangleCoordinate[0]);
        this.rectangleCoordinateY = Integer.valueOf(rectangleCoordinate[1]);
    }

    public Rover start() {
        RoverInputValidator roverInputValidator = new RoverInputValidator();
        boolean roverInputsIsValid = roverInputValidator.validate(roverFirstPosition,
                roverInstructions,
                rectangleCoordinateX,
                rectangleCoordinateY
        );

        if (!roverInputsIsValid) {
            System.out.println("Rover inputs is not valid! Please check your rover inputs.");
            return null;
        }

        String[] coordinates = this.roverFirstPosition.split(" ");

        Rover rover = Rover.builder()
                .x(Integer.parseInt(coordinates[0]))
                .y(Integer.parseInt(coordinates[1]))
                .direction(coordinates[2])
                .build();

        Stream.of(this.roverInstructions.split("")).forEach(instruction -> {
            decideAndRotateHeading(instruction, rover);
            roverMoving(rover);
        });

        System.out.println("Outputs : ");
        if (!roverLastPositionIsValid(rover)) {
            System.out.println("Rover left the area! Please check your instructions.");
            return null;
        }

        System.out.println(String.join(" ", String.valueOf(rover.getX()), String.valueOf(rover.getY()), rover.getDirection()));
        System.out.println("-----------");

        return rover;
    }

    private void decideAndRotateHeading(String instruction, Rover rover) {
        if (instruction.equals("L")) {
            rover.setDirection(leftRotationMap.get(rover.getDirection()));
        }

        if (instruction.equals("R")) {
            rover.setDirection(rightRotationMap.get(rover.getDirection()));
        }
    }

    private void roverMoving(Rover rover) {
        if (rover.getDirection().equals("W")) {
            rover.setX(rover.getX() - 1);
        } else if (rover.getDirection().equals("E")) {
            rover.setX(rover.getX() + 1);
        } else if (rover.getDirection().equals("N")) {
            rover.setY(rover.getY() + 1);
        } else if (rover.getDirection().equals("S")) {
            rover.setY(rover.getY() - 1);
        }
    }

    private boolean roverLastPositionIsValid(Rover rover) {
        if (rover.getX() < 0 || rover.getX() > this.rectangleCoordinateX || rover.getY() < 0 || rover.getY() > this.rectangleCoordinateY) {
            return false;
        }

        return true;
    }
}
