package com.example.demo_for_security.enum_;

import lombok.Getter;

@Getter
public enum ResType {
    MENU("菜单", 1),
    BUTTON("按钮", 2),
    INTERFACE("接口", 3);

    private String name;
    private Integer code;

    ResType(String name, Integer code) {
        this.name = name;
        this.code = code;
    }


}
