package com.ruslanburduzhan.pastebox.repository;

import com.ruslanburduzhan.pastebox.entity.PasteBoxEntity;

import java.util.List;

public interface PasteBoxRepository {
    PasteBoxEntity getByHash(String hash);
    List<PasteBoxEntity> getAllPublic(int amount);
    void add(PasteBoxEntity pasteBoxEntity);
}
