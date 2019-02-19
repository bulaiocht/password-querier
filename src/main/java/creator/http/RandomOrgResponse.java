package creator.http;

import lombok.Data;

@Data
public class RandomOrgResponse {
    private String jsonrpc;
    private RandomResult result;
    private long id;
}

