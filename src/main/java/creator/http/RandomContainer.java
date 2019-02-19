package creator.http;

import lombok.Data;

import java.util.List;

@Data
public class RandomContainer{
    private List<String> data;
    private String completionTime;
}
