package com.ruslanburduzhan.pastebox;

import com.ruslanburduzhan.pastebox.entity.PasteBoxEntity;
import com.ruslanburduzhan.pastebox.repository.PasteBoxRepository;
import com.ruslanburduzhan.pastebox.service.PasteBoxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteboxServiceTest {

    @Autowired
    private PasteBoxService pasteBoxService;

    @MockBean
    private PasteBoxRepository pasteBoxRepository;

    @Test
    public void notExistHash() {
        when(pasteBoxRepository.getByHash(anyString())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> pasteBoxService.getByHash("sdfhgjkldfshgjklfds"));
    }

    @Test
    public void getExistHash() {
        PasteBoxEntity test1 = new PasteBoxEntity();
        test1.setHash("1");
        when(pasteBoxRepository.getByHash("1")).thenReturn(test1);
    }
}
