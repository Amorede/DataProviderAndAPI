package dto;

public class AllStarWarsUsers {

    private Integer count;
    private String next;
    private String previous;
    private StarWarsUser[] results;

    public StarWarsUser[] getResults() {
        return results;
    }

}