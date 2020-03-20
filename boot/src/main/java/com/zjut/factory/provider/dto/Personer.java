package com.zjut.factory.provider.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Personer implements Serializable {

    private String name;

    private String city;

    private Integer age;
}
