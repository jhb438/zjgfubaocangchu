package com.basic.zjgfbcc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.InformationCategory;
import com.basic.zjgfbcc.entity.TreeNode;
import com.basic.zjgfbcc.service.InformationCategoryService;
import com.basic.zjgfbcc.service.InformationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author 
 * @date 2019-04-09 11:30:38
 */
@Api(value = "/栏目")
@RestController
@CrossOrigin
@RequestMapping("sys/informationcategory")
public class InformationCategoryController {
	@Autowired
	private InformationCategoryService informationCategoryService;

	@Autowired
	private InformationInfoService informationInfoService;
	
	/**
	 * 列表数据
	 */
	@PassToken
	@ApiOperation(value="栏目列表数据")
    @ResponseBody
	@RequestMapping(value="/listData",produces="application/json;charset=utf-8",method=RequestMethod.GET)
	public LayuiUtil listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<InformationCategory> informationCategoryList = informationCategoryService.getList(query);
		int total = informationCategoryService.getCount(query);
		PageUtils pageUtil = new PageUtils(informationCategoryList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

    /**
     * 新增
     **/
    @ApiOperation(value="栏目新增")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(InformationCategory model){
		//获取栏目编号
		if(model.getPcategoryCode()==null)
		{
			model.setPcategoryCode("");
		}
		String categoryCode=informationCategoryService.getNextCateGoryCode(model.getPcategoryCode());
		if(categoryCode==null||categoryCode.equals(""))
		{
			categoryCode=model.getPcategoryCode()+"001";
		}
		else
		{
			Integer nextCateGoryCode=Integer.parseInt(categoryCode)+1;
			categoryCode=String.format("%0"+categoryCode.length()+"d",nextCateGoryCode);
		}
		model.setCategoryCode(categoryCode);
		informationCategoryService.save(model);
        return R.ok();  
    }


	/**
	 * 修改
	 */
	@ApiOperation(value="栏目修改")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update( InformationCategory informationCategory){
		informationCategoryService.update(informationCategory);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="栏目删除")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String [] rowGuids){
		informationCategoryService.deleteBatch(rowGuids);
		return R.ok();
	}


	/**
	 * 查询栏目树
	 * <p>Title: getCategoryTrees</p>
	 * <p>Description: </p>
	 * @author hero
	 * @return
	 */
	@ApiOperation(value="查询栏目树-栏目列表使用")
	@ResponseBody
	@RequestMapping(value="/getCategoryTreeByInfo",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public List<TreeNode> getCategoryTreeByInfo(String id, String Name, String value){
		List<InformationCategory> TopTrees;
		if(value!=null)
		{
			TopTrees= informationCategoryService.findTopTreesByGuid(value);
		}
		else
		{
			TopTrees= informationCategoryService.findTopTrees("");
		}


		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (InformationCategory v : TopTrees) {
			TreeNode tn = new TreeNode();
			tn.id = v.getRowId();
			tn.name = v.getCategoryName();
			tn.value = v.getRowGuid();
			tn.click = "NodeChecked(this,'" +  v.getRowGuid() + "','" + v.getCategoryName() + "')";
			int Child = informationCategoryService.findTopTreesByGuid(v.getRowGuid()).size();
			tn.isParent = Child>0?1:0;

			nodes.add(tn);
		}

		return nodes;
	}
	

	/**
	 * 查询栏目树
	 * <p>Title: getCategoryTrees</p>
	 * <p>Description: </p>
	 * @author hero
	 * @return
	 */
	@ApiOperation(value="查询栏目树-栏目列表使用")
	@ResponseBody
	@RequestMapping(value="/getCategoryTree",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public List<TreeNode> getCategoryTree(String id, String Name, String value){
		String pcategory_code="";
		if(value!=null)
		{
			pcategory_code=value;
		}
		List<InformationCategory> TopTrees = informationCategoryService.findTopTrees(pcategory_code);

		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (InformationCategory v : TopTrees) {
			TreeNode tn = new TreeNode();
			tn.id = v.getRowId();
			tn.name = v.getCategoryName();
			tn.value = v.getCategoryCode();
			tn.click = "NodeChecked(this,'" +  v.getRowGuid() + "','" + v.getCategoryName() + "')";
			int Child = informationCategoryService.findTopTrees(v.getCategoryCode()).size();
			tn.isParent = Child>0?1:0;

			nodes.add(tn);
		}

		return nodes;
	}
    
    /**
     * 获取所有栏目（下拉框准备数据）
     * <p>Title: getAllCategory</p>  
     * <p>Description: </p>
     * @author hero  
     * @return
     */
    @ApiOperation(value="获取所有栏目-信息发布栏目选择使用")
    @ResponseBody
    @RequestMapping(value="/getAllCategory",produces="application/json;charset=utf-8",method=RequestMethod.GET)
    public List<TreeNode> getAllCategory(String infoGuid){
		List<InformationCategory> category = informationCategoryService.findTopTrees("");

		List<String> selfCategory = informationInfoService.selfCategory(infoGuid);
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (InformationCategory c : category) {
			List<TreeNode> children = getChildrenCategory(c, selfCategory);
			TreeNode tn = new TreeNode();
			tn.id = c.getRowId();
			tn.name = c.getCategoryName();
			tn.value = c.getRowGuid();
			tn.children = children;
			tn.isParent = (children.size() > 0) ? 1 : 0;

			if (selfCategory.contains(c.getRowGuid()))
			{
				tn.checked = true;
			}
			nodes.add(tn);
		}
		return nodes;
    }
	/**
	 * 获取所有子栏目（下拉框准备数据）

	 * <p>Description: </p>
	 * @author hero
	 * @return
	 */
	@ApiOperation(value="获取所有子栏目")
	@ResponseBody
	@RequestMapping(value="/getChildrenCategory",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public List<TreeNode> getChildrenCategory(InformationCategory model,List<String> selfCategory){
		List<TreeNode> children = new ArrayList<TreeNode>();
		List<InformationCategory> category = informationCategoryService.findTopTrees(model.getCategoryCode());
		if (category.size() > 0)
		{
			for(InformationCategory c: category)
			{
				List<TreeNode> cd = getChildrenCategory(c, selfCategory);
				TreeNode tn = new TreeNode();
				tn.id = c.getRowId();
				tn.name = c.getCategoryName();
				tn.value = c.getRowGuid();
				tn.children = cd;
				tn.isParent = (cd.size() > 0) ? 1 : 0;
				if (selfCategory.contains(c.getRowGuid()))
				{
					tn.checked = true;
				}
				children.add(tn);
			}
		}
		return children;

	}
    
    /**
     * 获取对应栏目
     * <p>Title: getCategoryByGuid</p>  
     * <p>Description: </p>
     * @author hero  
     * @return
     */
    @ApiOperation(value="获取对应栏目")
    @ResponseBody
    @RequestMapping(value="/getCategoryByGuid",produces="application/json;charset=utf-8",method=RequestMethod.GET)
    public R getCategoryByGuid(@RequestParam String categoryGuid){
		List<InformationCategory> informationCategoryList = informationCategoryService.getCategoryByGuid(categoryGuid);
		if (informationCategoryList == null) {
			return R.ok().put("data", "");
		}
		JSONArray array = new JSONArray();
    	for (InformationCategory v : informationCategoryList) {
    		JSONObject obj = new JSONObject();
        	obj.put("name", v.getCategoryName());
        	obj.put("value", v.getRowGuid());
        	obj.put("selected", "");
        	obj.put("disabled", "");
        	obj.put("is_needAudit", v.getIsNeedAudit());
        	array.add(obj);
		}
		return R.ok().put("data", array);
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
		InformationCategory model = informationCategoryService.getDetailByGuid(rowGuid);
		return R.ok().put("data",model);
	}
    
}
