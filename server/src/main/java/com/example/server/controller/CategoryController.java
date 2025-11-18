package com.example.server.controller;


import com.example.pojo.DTO.CategoryDTO;
import com.example.pojo.DTO.SongDTO;
import com.example.result.Result;
import com.example.server.service.CategoryService;
import com.example.server.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public Result save(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.save(categoryDTO);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("fail：" + e.getMessage());
        }
    }
    @PostMapping("/update")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result deletebyID(@PathVariable Long id) {
        categoryService.deletebyID(id);
        return Result.success();
    }
     @GetMapping("/getAllByUserId/{userId}")
    public Result getAllByUserId(@PathVariable Long userId) {
        return Result.success(categoryService.getAll(userId));
    }
}
