package hr.lknezevic.taskflow.taskflowgui.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private final Gson gson;

    public JsonUtil() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String toJson(Object object) {
        return this.gson.toJson(object);
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return this.gson.fromJson(json, clazz);
    }
}
