package creator.http;

import lombok.Data;

@Data
public class Params {
    private String apiKey;
    private int n;
    private long length;
    private String characters;
    private boolean replacement;
}
