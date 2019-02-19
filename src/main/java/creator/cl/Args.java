package creator.cl;

import com.beust.jcommander.Parameter;
import lombok.Data;

@Data
public class Args {

    @Parameter(names = {"--number", "-n"}, description = "Number of generated passwords")
    private Integer number = 1;

    @Parameter(names = {"--length", "-l"}, description = "Password length")
    private Long length = 16L;

    @Parameter(names = {"--file", "-f"}, description = "Name of file where the passwords will be stored")
    private String filename;

}
