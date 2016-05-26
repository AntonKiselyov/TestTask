package ru.yandex.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin on 26.05.2016.
 */
public class Variables {
    private Map<String,Double> vars;

    public Variables(Set<String> set) {
        vars = new HashMap<>();
        set.stream()
                .forEach(s -> {
                    vars.put(s,null);
                });
    }

    @JsonCreator
    public Variables(@JsonProperty("variables")  Map<String,Double> ars) {
        this.vars = vars;
    }

    public Map<String, Double> getVars() {
        return vars;
    }

    public void setVars(Map<String, Double> vars) {
        this.vars = vars;
    }

    @Override
    public String toString() {
        return "Variables {\'" + vars + "\'}";
    }
}
