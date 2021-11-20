import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rover {
    private int x;
    private int y;
    private String direction;
}
