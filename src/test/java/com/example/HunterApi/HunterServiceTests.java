package com.example.HunterApi;

import com.example.HunterApi.Exception.AlreadyExistException;
import com.example.HunterApi.Exception.NotFoundException;
import com.example.HunterApi.Exception.WrongAgeException;
import com.example.HunterApi.model.Entity.Hunter;
import com.example.HunterApi.model.HunterDto.HunterDTO;
import com.example.HunterApi.repository.HunterRepository;
import com.example.HunterApi.repository.HuntingClubRepository;
import com.example.HunterApi.service.HunterService;
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
public class HunterServiceTests {

    @Test
    void shouldNotFindHunterById_hunterDoesNotExist_throw_NotFoundEx(){
        var mockHunterRepo = mock(HunterRepository.class);
        when(mockHunterRepo.findById(anyLong())).thenReturn(Optional.empty());
        HunterService toTest = new HunterService(mockHunterRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.getHunterByIdService(anyLong());
        });
    }

    @Test
    void shouldNotAddNewHunter_wrongAge_throw_WrongAgeEx(){
        Hunter hunter =new Hunter();
        hunter.setAge(17);
        HunterService toTest = new HunterService(null,null,null);
        assertThrows(WrongAgeException.class, () -> {
            toTest.addHunterService(hunter);
        });

    }

    @Test
    void shouldNotAddNewHunter_hunterAlreadyExist_throw_AlreadyExistEx(){
        var mockHunterRepo = mock(HunterRepository.class);
        Hunter hunter =new Hunter();
        hunter.setAge(33);
        hunter.setSurname("surname");
        hunter.setName("name");
        when(mockHunterRepo.findByNameAndSurname(anyString(),anyString())).thenReturn(Optional.of(hunter));
        HunterService toTest = new HunterService(mockHunterRepo,null,null);
        assertThrows(AlreadyExistException.class, () -> {
            toTest.addHunterService(hunter);
        });
    }
    @Test
    void shouldNotDeleteHunter_hunterDoesNotExist_throw_NotFoundEx(){
        var mockHunterRepo = mock(HunterRepository.class);
        when(mockHunterRepo.findById(anyLong())).thenReturn(Optional.empty());
        HunterService toTest = new HunterService(mockHunterRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.deleteHunterService(anyLong());
        });
    }
    @Test
    void shouldNotAddHunterToClub_hunterDoesNotExist_throw_NotFoundEx(){
        var mockHunterRepo = mock(HunterRepository.class);
        when(mockHunterRepo.findById(1L)).thenReturn(Optional.empty());
        HunterService toTest = new HunterService(mockHunterRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.addingHunterToClubService(1L,anyLong());
        });
    }

    @Test
    void shouldNotAddHunterToClub_clubDoesNotExist_throw_NotFoundEx(){
        Hunter hunter = new Hunter();
        var mockHunterRepo = mock(HunterRepository.class);
        when(mockHunterRepo.findById(1L)).thenReturn(Optional.of(hunter));
        var mockClubRepo = mock(HuntingClubRepository.class);
        when(mockClubRepo.findById(1L)).thenReturn(Optional.empty());
        HunterService toTest = new HunterService(mockHunterRepo,mockClubRepo,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.addingHunterToClubService(1L,1L);
        });
    }

    @Test
    void shouldNotUpdateHunter_hunterDoesNotExist_throw_NotFoundEx(){
        HunterDTO hunterDTO = new HunterDTO();
        var mockHunterRepo = mock(HunterRepository.class);
        when(mockHunterRepo.findById(anyLong())).thenReturn(Optional.empty());
        HunterService toTest = new HunterService(mockHunterRepo,null,null);
        assertThrows(NotFoundException.class, () -> {
            toTest.updateHunter(anyLong(),hunterDTO);
        });
    }

    @Test
    void shouldNotUpdateHunter_wrongAge_throw_WrongAgeEx(){
        HunterDTO hunterDTO = new HunterDTO();
        hunterDTO.setAge(17);
        HunterService toTest = new HunterService(null,null,null);
        assertThrows(WrongAgeException.class, () -> {
            toTest.updateHunter(1L,hunterDTO);
        });
    }

    @Test
    void shouldNotUpdateHunter_hunterAlreadyExist_throw_AlreadyExistEx(){
        Hunter hunter=new Hunter();
        HunterDTO hunterDTO = new HunterDTO();
        hunterDTO.setName("name");
        hunterDTO.setSurname("surname");
        var mockHunterRepo = mock(HunterRepository.class);
        when(mockHunterRepo.findById(anyLong())).thenReturn(Optional.of(hunter));
        when(mockHunterRepo.findByNameAndSurname(anyString(),anyString())).thenReturn(Optional.of(hunter));
        HunterService toTest = new HunterService(mockHunterRepo,null,null);
        assertThrows(AlreadyExistException.class, () -> {
            toTest.updateHunter(anyLong(),hunterDTO);
        });
    }
}
