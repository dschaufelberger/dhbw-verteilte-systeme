package main.java;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Dodo on 09.03.2017.
 */
@JsonIgnoreProperties("password")
public class World {
    public String message;

    public String password = "superSecret";

    public World() {
    }

    public World(String message) {
        this.message = message;
    }
}
