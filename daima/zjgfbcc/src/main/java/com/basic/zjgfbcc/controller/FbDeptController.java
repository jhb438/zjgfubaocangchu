package com.basic.zjgfbcc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.entity.FbDept;
import com.basic.zjgfbcc.service.FbDeptService;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;


/**
 * 
 * 
 * @author 
 * @date 2020-04-19 12:51:01
 */
@Api(value = "")
@RestController
@CrossOrigin
@RequestMapping("sys/fbdept")
public class FbDeptController {
	@Autowired
	private FbDeptService fbDeptService;
	
	/**
	 * 列表数据
	 */
	@PassToken
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/listData",produces="application/json;charset=utf-8",method=RequestMethod.GET)
	public LayuiUtil listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FbDept> fbDeptList = fbDeptService.getList(query);
		int total = fbDeptService.getCount(query);
		PageUtils pageUtil = new PageUtils(fbDeptList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

	/**
	 * 查询部门树
	 * <p>Title: getdeptTrees</p>
	 * <p>Description: </p>
	 *
	 * @return
	 * @author hero
	 */
	@ApiOperation(value = "查询部门树")
	@ResponseBody
	@RequestMapping(value = "/getDeptTree", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public List<TreeNode> getdeptTree(String id, String name, String value) {
		String deptcode = "root000000";
		if (value != null) {
			deptcode = value;
		}

		List<FbDept> trees = fbDeptService.findDepts(deptcode);

		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (FbDept v : trees) {
			TreeNode tn = new TreeNode();
			tn.id = v.getRowId();
			tn.name = v.getOrgName();
			tn.value = v.getOrgIndexCode();
			tn.click = "NodeChecked(this,'" + v.getOrgIndexCode() + "','" + v.getOrgName() + "','" + v.getRowGuid() + "')";
			int Child = fbDeptService.findDepts(v.getOrgIndexCode()).size();
			tn.isParent = Child > 0 ? 1 : 0;

			nodes.add(tn);
		}
		return nodes;
	}


	/**
     * 新增
     **/
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(FbDept fbDept){
		fbDeptService.save(fbDept);
        return R.ok();  
    }

	/**
	 * 修改
	 */
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update(FbDept fbDept){
		fbDeptService.update(fbDept);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String[] rowGuids){
		fbDeptService.deleteBatch(rowGuids);
		return R.ok();
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
		FbDept model = fbDeptService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }


	
}
