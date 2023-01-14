package classwork.day21;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyParser {

    private static final String JSON = "src/test/java/classwork/day21/recipe.json";

    public static void parseGSON() throws IOException {

        Gson gson = new Gson();

        Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);

        System.out.println(recipe.getIngredlist());
        System.out.println(recipe.getPreptime());
        System.out.println(recipe.getRecipename());
    }

    public static void toGSON() throws IOException {

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Ingredient> ingredlist = new ArrayList<>();
        ingredlist.add(new Ingredient("Бульба", 5));
        ingredlist.add(new Ingredient("Cucumber", 1));
        Recipe recipe = new Recipe("", ingredlist, 120);
//        Recipe recipe = new Recipe("", List.of(new Ingredient("myaso", 100)), 120);
        System.out.println(gson.toJson(recipe));

    }

    public static String fromGSON(Search search) {

        Gson gson = new Gson();
        return gson.toJson(search);

    }

    public static void main(String[] args) throws IOException {

//        parseGSON();
        toGSON();
    }
}
