package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类ID查询套餐数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from setmeal where category_id=#{categoryId} ")
    Integer countByCategoryId(Long categoryId);


    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    Page<SetmealVO> page(SetmealPageQueryDTO setmealPageQueryDTO);


    SetmealVO getById(Long id);

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    Integer statusCheck(List<Long> ids);

    void deleteBatch(List<Long> ids);
}
