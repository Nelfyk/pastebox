package com.ruslanburduzhan.pastebox.repository;

import com.ruslanburduzhan.pastebox.entity.PasteBoxEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PasteBoxRepositoryMap implements PasteBoxRepository {

    private final Map<String, PasteBoxEntity> vault = new HashMap<>();

    @Override
    public PasteBoxEntity getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = vault.get(hash);
        if (pasteBoxEntity == null)
            throw new RuntimeException("PasteBox not found with hash = " + hash);
        return pasteBoxEntity;
    }

    @Override
    public List<PasteBoxEntity> getAllPublic(int amount) {
        LocalDateTime currentTime = LocalDateTime.now();
        return vault.values().stream()
                .filter(PasteBoxEntity::isPublic)
                .filter(e->e.getLifeTime().isAfter(currentTime))
                .sorted(Comparator.comparing(PasteBoxEntity::getId).reversed())
                .limit(amount)
                .toList();
    }

    @Override
    public void add(PasteBoxEntity pasteBoxEntity) {
        vault.put(pasteBoxEntity.getHash(),pasteBoxEntity);
    }
}
