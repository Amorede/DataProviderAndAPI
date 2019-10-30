package dto;

import java.util.List;

public class StarWarsUser {

    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private List<String> films;
    private String[] species;
    private String[] vehicles;
    private String[] starships;
    private String created;
    private String edited;
    private String url;

    public String getName() {
        return name;
    }
}
