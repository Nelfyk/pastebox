package com.ruslanburduzhan.pastebox.service;

import com.ruslanburduzhan.pastebox.api.request.PasteBoxRequest;
import com.ruslanburduzhan.pastebox.api.request.PublicStatus;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxResponse;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxUrlResponse;
import com.ruslanburduzhan.pastebox.entity.PasteBoxEntity;
import com.ruslanburduzhan.pastebox.repository.PasteBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PasteBoxServiceImpl implements PasteBoxService {

//    private final String host = "https://pastebox.rb.ru";
    private final String host = "";
    private final int publicListSize = 10;

    private final PasteBoxRepository repository;

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity entity = repository.getByHash(hash);
        return new PasteBoxResponse(entity.getData(),entity.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getAllPublic() {
        return null;
    }

    @Override
    public PasteBoxUrlResponse addPaste(PasteBoxRequest request) {

        PasteBoxEntity entity = new PasteBoxEntity(request.getData(),request.getPublicStatus()== PublicStatus.PUBLIC);
        repository.add(entity);
        return new PasteBoxUrlResponse(host + "/" + entity.getHash());
    }
}
