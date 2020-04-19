package ua.mai.fam.restclient.client;

import org.springframework.web.client.RestTemplate;
import ua.mai.fam.restclient.FamClient;

public class EntityClient {

    private FamClient appClient;
    private String entityPath;

    public EntityClient(FamClient appClient, String entityPath) {
        this.appClient = appClient;
        this.entityPath = entityPath;
    }

    public FamClient getAppClient() {
        return appClient;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public String getEntityUri() {
        return appClient.getBaseUri() + "/" + entityPath;
    }

    public RestTemplate getRestTemplate() {
        return appClient.getRestTemplate();
    }

}
