package com.example.server.service;

import com.example.pojo.DTO.SongDTO;
import com.example.pojo.entity.Song;
import com.example.server.mapper.SongMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongMapper songMapper;

    public void save(SongDTO songDTO) {
        Song song = new Song();
        BeanUtils.copyProperties(songDTO, song);
        song.setCreatedDate(LocalDate.now());
        song.setUpdatedDate(LocalDate.now());
        song.setIsArchived(0);
        song.setIsDeleted(0);
        songMapper.insert(song);
    }

    public void deletebyID(Long id) {
        songMapper.deletebyID(id, LocalDate.now());
    }

    public void update(SongDTO songDTO) {
        Song song = songMapper.selectById(songDTO.getId());
        if(song == null) {
            throw new IllegalArgumentException("Song not found");
        }
        else{
            BeanUtils.copyProperties(songDTO, song);
            song.setUpdatedDate(LocalDate.now());
            songMapper.update(song);
        }
    }
}
