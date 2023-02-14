package com.ruslanburduzhan.pastebox.service;

import com.ruslanburduzhan.pastebox.api.request.PasteBoxRequest;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxResponse;
import com.ruslanburduzhan.pastebox.api.response.PasteBoxUrlResponse;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getAllPublic();
    PasteBoxUrlResponse addPaste(PasteBoxRequest request);
}
