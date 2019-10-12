package apimanager;

public class ApiManager {

    private ApiTest1Helper apiTest1Helper;

    private ApiTest2Helper apiTest2Helper;

    public void dealWithApi() {
        apiTest1Helper = new ApiTest1Helper();
        apiTest2Helper = new ApiTest2Helper();
    }

    public ApiTest1Helper getApiTest1Helper() {
        return apiTest1Helper;
    }

    public ApiTest2Helper getApiTest2Helper() {
        return apiTest2Helper;
    }

}
