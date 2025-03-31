package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    List<Long> selectSetmealIdsByDishId(List<Long> dishIds);

    void insert(SetmealDish setmealDish);

    void insertBatch(List<SetmealDish> setmealDishs);

    List<SetmealDish> getBySetmealId(Long id);

    void deleteBySetmealId(Long id);

    Integer countUnSaledDishBySetmealId(Long id);
}
