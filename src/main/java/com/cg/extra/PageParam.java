//package com.cg.extra;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiParam;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//
///**
// * @Date: 2021/03/17 15:17
// * @descript:
// */
//@ApiModel(value = "PageParam", description = "查询分页参数")
//@Getter
//@Setter
//public class PageParam {
//
//    @ApiParam(value = "当前页码", name = "pageIndex", defaultValue = "1", example = "1")
//    @Min(value = 1, message = "页码不得小于1")
//    private int current = 1;
//
//    @ApiParam(value = "每页条数", name = "pageSize", defaultValue = "20", example = "20")
//    @Min(value = 1, message = "每页条数不得小于1")
//    @Max(value = 100, message = "每页条数不得大于100")
//    private int size = 20;
//
//    private Page page;
//
//    public PageParam(int size) {
//        this.size = size;
//    }
//
//    public PageParam() {
//    }
//
//    public Page getPage() {
//        return new Page(current, size);
//    }
//
//    public Page getPage(long paramPageSize) {
//        return new Page(current, paramPageSize);
//    }
//
//}