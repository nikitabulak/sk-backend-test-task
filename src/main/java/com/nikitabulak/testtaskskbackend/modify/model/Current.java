package com.nikitabulak.testtaskskbackend.modify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {
    private double current;

    public void increaseCurrent(double add) {
        current += add;
    }
}
