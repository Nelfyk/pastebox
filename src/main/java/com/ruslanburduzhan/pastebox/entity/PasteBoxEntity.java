package com.ruslanburduzhan.pastebox.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

@Data
public class PasteBoxEntity {
    private static int counter = 0;
    private int id;
    private String data;
    private boolean isPublic;
    private LocalDateTime lifeTime;
    private String hash;

    public PasteBoxEntity() {
    }

    public PasteBoxEntity(String data, boolean isPublic, LocalDateTime lifeTime) {
        this.id = counter;
        this.data = data;
        this.isPublic = isPublic;
        this.lifeTime = lifeTime;
        this.hash = generateHash();
        counter++;
    }

    private String generateHash() {
        return Integer.toHexString(hashCode() + (new Random().nextInt(120) * counter));
    }

}
