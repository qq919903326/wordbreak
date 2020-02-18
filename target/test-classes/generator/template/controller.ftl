package ${basePackage}.web;
import ${basePackage}.core.api.ApiResult;
import ${basePackage}.core.api.ApiResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

/**
* @author: ${author}
* @date: ${date}
* @description: ${modelNameUpperCamel}控制器
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;


    /**
     * 新增${modelNameUpperCamel}
     */
    @PostMapping("/add")
    public ApiResult add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        boolean flag = ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        if (!flag) {
            return ApiResultGenerator.badParameter("新增失败！");
        }
        return ApiResultGenerator.success();
    }

    /**
     * 删除${modelNameUpperCamel}
     */
    @DeleteMapping("/delete")
    public ApiResult delete(@RequestParam Integer id) {
        boolean flag = ${modelNameLowerCamel}Service.deleteById(id);
        if (!flag) {
            return ApiResultGenerator.badParameter("被删除对象不存在！");
        }
        return ApiResultGenerator.success();
    }

    /**
     * 更新${modelNameUpperCamel}
     */
    @PutMapping("/update")
    public ApiResult update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        boolean flag = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        if (!flag) {
            return ApiResultGenerator.badParameter("更新失败！");
        }
        return ApiResultGenerator.success();
    }

    /**
     * ${modelNameUpperCamel}详情
     */
    @GetMapping("/detail")
    public ApiResult detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        if (${modelNameLowerCamel} == null) {
            return ApiResultGenerator.badParameter("查找对象不存在！");
        }
        return ApiResultGenerator.success(${modelNameLowerCamel});
    }

    /**
     * ${modelNameUpperCamel}分页列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     */
    @GetMapping("/pageList")
    public ApiResult pageList(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "0") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ApiResultGenerator.success(pageInfo);
    }
}
