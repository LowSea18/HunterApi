package com.example.HunterApi;

import com.example.HunterApi.Exception.AlreadyExistException;
import com.example.HunterApi.Exception.NotFoundException;
import com.example.HunterApi.model.Entity.HuntingClub;
import com.example.HunterApi.model.HuntingClubDto.UpdateClubDto;
import com.example.HunterApi.repository.HuntingClubRepository;
import com.example.HunterApi.service.HuntingClubService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HuntingClubServiceTests {
    @Test
    void shouldNotAddNewHuntingClub_huntingClubAlreadyExist_throw_AlreadyExistEx(){
        var mockHuntingClubRepo = mock(HuntingClubRepository.class);
        HuntingClub huntingClub = new HuntingClub();
        huntingClub.setName("AnyString");
        when(mockHuntingClubRepo.findByName(anyString())).thenReturn(Optional.of(huntingClub));
        HuntingClubService toTest = new HuntingClubService(mockHuntingClubRepo,null);
        assertThrows(AlreadyExistException.class, () -> {
            toTest.addHuntingClubService(huntingClub);
        });
    }
    @Test
    void shouldNotFindClubById_clubNotFound_throw_NotFoundEx(){
        var mockHuntingClubRepo = mock(HuntingClubRepository.class);
        when(mockHuntingClubRepo.findById(anyLong())).thenReturn(Optional.empty());
        HuntingClubService toTest = new HuntingClubService(mockHuntingClubRepo,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.getHuntingClubByIdService(anyLong());
        });
    }
    @Test
    void shouldNotUpdateClub_clubDoesNotExist_throw_NotFoundEx(){
        UpdateClubDto updateClubDto = new UpdateClubDto();
        var mockHuntingClubRepo = mock(HuntingClubRepository.class);
        when(mockHuntingClubRepo.findById(anyLong())).thenReturn(Optional.empty());
        HuntingClubService toTest = new HuntingClubService(mockHuntingClubRepo,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.updateClub(anyLong(),updateClubDto);
        });
    }
    @Test
    void shouldNotUpdateClub_clubAlreadyExist_throw_AlreadyExistEx(){
        UpdateClubDto updateClubDto = new UpdateClubDto();
        updateClubDto.setName("name");
        HuntingClub huntingClub = new HuntingClub();
        huntingClub.setName("name");
        var mockHuntingClubRepo = mock(HuntingClubRepository.class);
        when(mockHuntingClubRepo.findById(anyLong())).thenReturn(Optional.of(huntingClub));
        when(mockHuntingClubRepo.findByName(anyString())).thenReturn(Optional.of(huntingClub));
        HuntingClubService toTest = new HuntingClubService(mockHuntingClubRepo,null);

        assertThrows(AlreadyExistException.class, () -> {
            toTest.updateClub(anyLong(),updateClubDto);
        });
    }
}
