package com.basic.zjgfbcc.controller;

import com.alibaba.fastjson.JSONArray;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_Module;
import com.basic.zjgfbcc.entity.TreeNode;
import com.basic.zjgfbcc.service.Frame_ModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模块管理 信息操作处理
 * 部门控制层
 * @author hero
 * @date 2019-03-06
 */
@CrossOrigin
@Api(value = "模块")
@RestController
@RequestMapping("sys/modular") 
public class Frame_ModuleController {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(Frame_ModuleController.class);

    @Autowired 
    private Frame_ModuleService moduleService;             
  
    /**   
     * 查询模块列表   
     * <p>Title: getDept</p>    
     * <p>Description: 模块</p>
     * @author hero
     * @param params
     * @return
     */
    @PassToken
    @ApiOperation(value = "查询模块列表")
    @ResponseBody
    @RequestMapping(value = "/getModular",produces="application/json;charset=utf-8",method= RequestMethod.GET)
    public LayuiUtil getDept(@RequestParam Map<String, Object> params){
    	Query query = new Query(params);
    	List<Frame_Module> list = moduleService.selectFrameModuleList(query);
		int total = moduleService.getCount(query);
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增模块
     * <p>Title: addDept</p>
     * <p>Description: 模块</p>
     * @author hero
     * @param module
     * @return
     */
    @ApiOperation(value = "新增模块")
    @ResponseBody
    @RequestMapping(value = "/add",produces="application/json;charset=utf-8",method= RequestMethod.POST)
    public R addDept(Frame_Module module){
		moduleService.insertFrameModule(module);

        //更新moduleCode
        Frame_Module model=moduleService.getDetailByGuid(module.getRowGuid());
        String moduleCode="";
        if(model.getPmoduleCode()==null||model.getPmoduleCode().equals(""))
        {
            moduleCode=model.getRowId().toString();
        }
        else
        {
            moduleCode=model.getPmoduleCode()+"."+model.getRowId().toString();
        }
        model.setModuleCode(moduleCode);
        moduleService.updateFrameModule(model);
        return R.ok();  
    }

    /**
     * 修改模块
     * <p>Title: addDept</p>
     * <p>Description: 模块</p>
     * @author hero

     * @return
     */
    @ApiOperation(value = "修改模块")
    @ResponseBody
    @RequestMapping(value = "/updateModule",produces="application/json;charset=utf-8",method= RequestMethod.POST)
    public R updateModule(Frame_Module module){
    	moduleService.updateFrameModule(module);
        return R.ok();
    }

    /**
     * 删除模块
     * <p>Title: deleteDept</p>
     * <p>Description: 模块</p>
     * @author hero

     * @return
     */
    @ApiOperation(value="删除模块")
	@ResponseBody
	@RequestMapping(value="/deleteModule",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R deleteModule(@RequestParam(value="rowGuids[]") String [] rowGuids){
        for(int i=0;i<rowGuids.length;i++)
        {
            Frame_Module model=moduleService.getDetailByGuid(rowGuids[i]);
            moduleService.deleteAllChild(model.getModuleCode());
        }
    	moduleService.deleteFrameModuleById(rowGuids);
        return R.ok();
    }

    /**
     * 保存模块
     * <p>Title: deleteDept</p>
     * <p>Description: 模块</p>
     * @author hero

     * @return
     */
    @ApiOperation(value="保存列表数据")
    @ResponseBody
    @RequestMapping(value="/saveRow",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R saveRow(@RequestParam(value="arrGuid[]") String [] arrGuid,@RequestParam(value="arrSortSQ[]") String [] arrSortSQ,
                     @RequestParam(value="arrTarget[]") String [] arrTarget,@RequestParam(value="arrIsVisible[]") String [] arrIsVisible){
      Frame_Module model=new Frame_Module();
      for(int i=0;i<arrGuid.length;i++) {
          model.setRowGuid(arrGuid[i]);
          model.setIsVisible(Integer.parseInt(arrIsVisible[i].toString()));
          model.setTarget(arrTarget[i]);
          model.setSortSq(Integer.parseInt(arrSortSQ[i]));
          moduleService.updateFrameModule(model);
      }
        return R.ok();
    }
    
    /**
     * 获取树
     * <p>Title: getTrees</p>  
     * <p>Description: </p>
     * @author hero  
     * @return
     */
    @ApiOperation(value="获取树")
    @ResponseBody
    @RequestMapping(value="/getTrees",produces="application/json;charset=utf-8",method=RequestMethod.GET)
    public R getTrees(){
    	JSONArray trees = moduleService.getTrees();
    	return R.ok().put("data", trees);
    }
       
    /**    
     * 查询复选框模块树   
     * <p>Title: getdeptTrees</p>   
     * <p>Description: </p> 
     * @author hero  
     * @return
     */
    @ApiOperation(value="查询复选框模块树")
	@ResponseBody
	@RequestMapping(value="/getModuleTrees",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getModuleTrees(){
    	JSONArray trees = moduleService.findModules();
    	return R.ok().put("data", trees);
    }

    /**
     * 模块树
     * <p>Title: getdeptTrees</p>
     * <p>Description: </p>
     * @author hero
     * @return
     */
    @ApiOperation(value="模块树")
    @ResponseBody
    @RequestMapping(value="/getSubModule",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public List<TreeNode> getSubModule(String id, String name, String value){
        String modulecode="";
        if(value!=null)
        {
            modulecode=value;
        }

        List<Frame_Module> trees = moduleService.getSubModules(modulecode);

        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (Frame_Module v : trees) {
            TreeNode tn = new TreeNode();
            tn.id = v.getRowId();
            tn.name = v.getModuleName();
            tn.value = v.getModuleCode();
            tn.click = "NodeChecked(this,'" +  v.getModuleCode() + "','" +v.getModuleName() + "')";
            int Child = moduleService.getSubModules(v.getModuleCode()).size();
            tn.isParent = Child>0?1:0;

            nodes.add(tn);
        }
        return nodes;
    }

    /**
     * 通过rowGuid获取一条记录
     * @param rowGuid
     * @return
     */
    @PassToken
    @ApiOperation(value="通过rowGuid获取一条记录")
    @ResponseBody
    @RequestMapping(value="/getDetailByGuid",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getDetailByGuid(@RequestParam String rowGuid){
        Frame_Module frame_module = moduleService.getDetailByGuid(rowGuid);
        return R.ok().put("data",frame_module);
    }
    
    
    /**
     * 通过菜单名获取一条记录
     * @param rowGuid
     * @return
     */
    @PassToken
    @ApiOperation(value="通过菜单名称获取一条记录")
    @ResponseBody
    @RequestMapping(value="/getDetailByMenuName",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getDetailByMenuName(@RequestParam String menuName){
        Frame_Module frame_module = moduleService.getDetailByMenuName(menuName);
        return R.ok().put("data",frame_module);
    }
    
    /**
     * 查询上级模块
     * <p>Title: getByModuleCode</p>
     * <p>Description: </p>
     * @author wzl
     * @return
     */
    @ApiOperation(value = "查询上级模块")
    @ResponseBody
    @RequestMapping(value="/getByModuleCode/{moduleCode}",produces="application/json;charset=utf-8",method=RequestMethod.GET)
    public R getByModuleCode(@PathVariable("moduleCode") String moduleCode){
        String name  = moduleService.getByModuleCode(moduleCode);
        return R.ok().put("data",name);
    }


}
