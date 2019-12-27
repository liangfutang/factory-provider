package com.zjut.dubbo.provider.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class TestDubboValidationDto implements Serializable {

    @NotNull(message = "主键id不能为空")
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    private Integer age;

    private String sex;

    public interface Register{}
}
