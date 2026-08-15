package ru.thevalidator.telegramtoxicbot.data.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.thevalidator.telegramtoxicbot.data.exception.NoDataFoundException;
import ru.thevalidator.telegramtoxicbot.data.repository.NoReplyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoReplyServiceImplTest {

    @Mock
    private NoReplyRepository mockRepository;

    @Test
    void getNextPhraseWhenEmptyList() {
        var service = new NoReplyServiceImpl(mockRepository);
        when(mockRepository.getAll()).thenReturn(new ArrayList<>());
        assertThrows(NoDataFoundException.class, service::getNextPhrase);
    }

    @Test
    void getNextPhraseWhenNotEmptyList() {
        var service = new NoReplyServiceImpl(mockRepository);
        when(mockRepository.getAll()).thenReturn(List.of("no_1", "no_2"));
        String result = service.getNextPhrase();
        assertEquals("no_1", result);
    }

}