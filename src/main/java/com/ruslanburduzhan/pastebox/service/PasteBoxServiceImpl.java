package com.ruslanburduzhan.pastebox.service;

import com.ruslanburduzhan.pastebox.api.request.PasteBoxRequest;
import com.ruslanburduzhan.pastebox.api.request.PublicStatus;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxResponse;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxUrlResponse;
import com.ruslanburduzhan.pastebox.entity.PasteBoxEntity;
import com.ruslanburduzhan.pastebox.repository.PasteBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasteBoxServiceImpl implements PasteBoxService {

    private final String host = "https://pastebox.rb.ru";
    private final int publicListSize = 10;

    private final PasteBoxRepository repository;

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity entity = repository.getByHash(hash);
        return new PasteBoxResponse(entity.getData(), entity.isPublic(), hash);
    }

    @Override
    public List<PasteBoxResponse> getAllPublic() {
        return entityToPasteBoxResponse(repository.getAllPublic(publicListSize));
    }

    @Override
    public PasteBoxUrlResponse addPaste(PasteBoxRequest request) {
        PasteBoxEntity entity = new PasteBoxEntity(request.getData(),
                request.getPublicStatus() == PublicStatus.PUBLIC,
                LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(entity);
        return new PasteBoxUrlResponse(host + "/" + entity.getHash());
    }

    private List<PasteBoxResponse> entityToPasteBoxResponse(List<PasteBoxEntity> entityList) {
        return entityList.stream().map(e -> new PasteBoxResponse(e.getData(), e.isPublic(), e.getHash())).toList();
    }
}
