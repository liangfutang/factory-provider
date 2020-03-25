package com.zjut.factory.provider.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Personer implements Serializable {

    private String name;

    private String city;

    private Integer age;

    public Personer(){}

    private Personer(Builder builder) {
        name = builder.name;
        city = builder.city;
        age = builder.age;
    }

    public static class Builder {
        private String name;
        private String city;
        private Integer age;

        public Builder name(String var) {
            name = var;
            return this;
        }

        public Builder city(String var) {
            city = var;
            return this;
        }

        public Builder age(Integer var) {
            age = var;
            return this;
        }

        public Personer builder() {
            return new Personer(this);
        }
    }
}
