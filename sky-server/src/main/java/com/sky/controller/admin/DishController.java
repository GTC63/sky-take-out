package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "菜品相关接口")
@Slf4j
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @ApiOperation("新增菜品")
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 菜品分页
     *
     * @param dishPageQueryDTO
     * @return
     */
    @ApiOperation("菜品分页")
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询{}", dishPageQueryDTO);
        PageResult page = dishService.page(dishPageQueryDTO);
        return Result.success(page);
    }

    /**
     * 删除菜品
     *
     * @param ids
     * @return
     */
    @ApiOperation("菜品批量删除")
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids) {
        log.info("删除菜品{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据Id查询菜品信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询菜品")
    @GetMapping("/{id}")
    public Result<DishVO> findById(@PathVariable Long id) {
        log.info("查询菜品IDP{}", id);
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return
     */
    @ApiOperation("修改菜品")
    @PutMapping
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品信息{}", dishDTO);
        dishService.update(dishDTO);
        return Result.success();
    }

    /**
     * 菜品起售停售
     *
     * @param status
     * @param id
     * @return
     */
    @ApiOperation("修改起售停售")
    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status, Long id) {
        log.info("启用禁用菜品信息{}", status);
        dishService.updateStatus(status, id);
        return Result.success();
    }

    /**
     * 根据分类Id查询裁菜品
     *
     * @param categoryId
     * @return
     */
    @ApiOperation("根据分类ID查询菜品")
    @GetMapping("/list")
    public Result<List<Dish>> findByCategoryId(Long categoryId) {
        log.info("查询分类ID为{}的菜品", categoryId);
        List<Dish> dishes = dishService.findByCategoryId(categoryId);
        return Result.success(dishes);
    }

}
