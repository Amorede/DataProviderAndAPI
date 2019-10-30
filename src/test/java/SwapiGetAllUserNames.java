
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import dto.AllStarWarsUsers;
import dto.StarWarsUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SwapiGetAllUserNames {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        SwapiGetAllUserNames swapiGetAllUserNames = new SwapiGetAllUserNames();
        List<String> listOfNames = new ArrayList<>();
        Gson gson = new Gson();

        String response = swapiGetAllUserNames.run("https://swapi.co/api/people");
        AllStarWarsUsers entity = gson.fromJson(response, AllStarWarsUsers.class);
        StarWarsUser[] swu = entity.getResults();
        for (StarWarsUser user : swu) {
            listOfNames.add(user.getName());
        }
        System.out.println(listOfNames);
    }
}