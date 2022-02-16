package pojo;

import java.util.List;

public class Result {
    private String formatted_address;
    private String formatted_phone_number;
    private String name;
    private List<Photos> photos;
    private double rating;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
}
