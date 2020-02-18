package com.wordbreak.entity;

import lombok.Data;

@Data
public class Dictionary {
    String dict;
    public Dictionary(String dict) {
        this.dict = dict;
    }
}
