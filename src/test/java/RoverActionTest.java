import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RoverActionTest {

    @Test
    public void shouldReturnLastCoordinateAndHeading() {
        String roverInputs = new StringBuilder("5 5\n")
                .append("1 2 N\n")
                .append("LMLMLMLMM\n")
                .toString();

        String[] inputRows = roverInputs.split("\n");

        RoverAction roverAction = new RoverAction(inputRows[0], inputRows[1], inputRows[2]);
        Rover rover = roverAction.start();

        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals("N", rover.getDirection());
    }

    @Test
    public void shouldReturnNullIfInputsAreNotValidate() {
        String roverInputs = new StringBuilder("5 5\n")
                .append("1 t N\n") // not valid input
                .append("LMLMLMLMM\n")
                .toString();

        String[] inputRows = roverInputs.split("\n");

        RoverAction roverAction = new RoverAction(inputRows[0], inputRows[1], inputRows[2]);
        Rover rover = roverAction.start();

        assertNull(rover);
    }

    @Test
    public void shouldReturnNullIfRoverLeftTheArea() {
        String roverInputs = new StringBuilder("5 5\n")
                .append("1 3 N\n")
                .append("MMMMMMMM\n") // for the rover to move out of the area.
                .toString();

        String[] inputRows = roverInputs.split("\n");

        RoverAction roverAction = new RoverAction(inputRows[0], inputRows[1], inputRows[2]);
        Rover rover = roverAction.start();

        assertNull(rover);
    }

}