package resources;

public enum ApiResources {
    getNearbyPlace("/maps/api/place/nearbysearch/json")
    ,getPlaceDetails("/maps/api/place/details/json")
    ,getPlacePhoto("/maps/api/place/photo");

    private final String resource;

    ApiResources(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
