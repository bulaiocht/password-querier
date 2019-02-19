package creator.http;

import lombok.Data;

@Data
public class RandomOrgRequest {
    private String jsonrpc;
    private String method;
    private Params params;
    private long id;
}
