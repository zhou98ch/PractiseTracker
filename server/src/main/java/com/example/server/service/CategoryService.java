package com.example.server.service;

import com.example.pojo.DTO.CategoryDTO;
import com.example.pojo.DTO.SongDTO;
import com.example.pojo.entity.Category;
import com.example.pojo.entity.Song;
import com.example.server.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper categoryMapper;

    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setCreatedDate(LocalDate.now());
        category.setUpdatedDate(LocalDate.now());
        category.setIsArchived(0);
        category.setIsDeleted(0);
        categoryMapper.insert(category);
    }

    public void deletebyID(Long id) {
        categoryMapper.deletebyID(id, LocalDate.now());
    }

    public void update(CategoryDTO categoryDTO) {
        Category category = categoryMapper.selectById(categoryDTO.getId());
        if(category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        else{
            BeanUtils.copyProperties(categoryDTO, category);
            category.setUpdatedDate(LocalDate.now());
            categoryMapper.update(category);
        }
    }

    public Object getAll(Long userId) {
        return categoryMapper.selectAll(userId);
    }
}
