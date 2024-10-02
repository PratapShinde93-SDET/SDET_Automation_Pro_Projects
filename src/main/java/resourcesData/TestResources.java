package resourcesData;
// Enum is special claas in java which has collection of constants  or methods
public enum TestResources {

    addplace("/maps/api/place/add/json"),
    GetPlaceEndP("/maps/api/place/get/json"),
    DeletePlaceEndP("maps/api/place/delete/json");
    private String resource;

    TestResources(String resource){

        this.resource=resource;
    }

    public String getretunresource(){

        return resource;
    }
}
