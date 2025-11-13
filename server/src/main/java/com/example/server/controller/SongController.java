package com.example.server.controller;

import com.example.pojo.DTO.SongDTO;
import com.example.pojo.DTO.UserDTO;
import com.example.result.Result;
import com.example.server.service.SongService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/create")
    public Result save(@RequestBody SongDTO songDTO) {
        songService.save(songDTO);
        return Result.success();
    }
    @PostMapping("/update")
    public Result update(@RequestBody SongDTO songDTO) {
        songService.update(songDTO);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result deletebyID(@PathVariable Long id) {
        songService.deletebyID(id);
        return Result.success();
    }

    @GetMapping("/list/{category_id}")
    public Result listByCategory(@PathVariable Long category_id) {
        return Result.success(songService.listByCategory(category_id));
    }
}
