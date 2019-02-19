package creator.http;

import lombok.Data;

@Data
public class RandomResult{
    private long bitsUsed;
    private long bitsLeft;
    private long requestsLeft;
    private long advisoryDelay;
    private RandomContainer random;
}
