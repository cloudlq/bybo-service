package com.mxg.bybo.rest;  
import com.mxg.bybo.service.GreenChanneService;
import com.mxg.bybo.model.GreenChanne;
import basic.common.core.dto.PageDto;
import basic.common.core.id.IdUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.google.common.collect.Lists;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  GreenChanneController
 *
 * @version : Ver 1.0
 * @date	: 2017-3-30
 */
@RestController
@Api(value = "GreenChanneController", description = "绿色通道相关")
@RequestMapping(value = "/greenChanne")
public class GreenChanneController { 
	
	   @InitBinder   
	    public void initBinder(WebDataBinder binder) {   
	        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        dateFormat.setLenient(true);   
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
	    }  
	
	@Autowired
	private GreenChanneService greenChanneService;
	
	@ApiOperation(value = "新增绿色通道", notes = "新增绿色通道")
	@RequestMapping(value = "/insertGreenChanne", method = RequestMethod.POST)
	public int insertGreenChanne(@RequestBody GreenChanne greenChanne){
		greenChanne.setId(IdUtil.getId());
		return greenChanneService.insertGreenChanne(greenChanne);
	}
	@ApiOperation(value = "批量新增绿色通道", notes = "批量新增绿色通道")
	@RequestMapping(value = "/insertGreenChanneBatch", method = RequestMethod.POST)
	public int insertGreenChanneBatch(@RequestBody List<GreenChanne> list){
		return greenChanneService.insertGreenChanneBatch(list);
	}
	@ApiOperation(value = "根据ID修改绿色通道", notes = "根据ID修改绿色通道")
	@RequestMapping(value = "/updateGreenChanneById", method = RequestMethod.POST)
	public int updateGreenChanneById(@RequestBody GreenChanne greenChanne){
		return greenChanneService.updateGreenChanneById(greenChanne);
	}
	@ApiOperation(value = "根据ID删除绿色通道", notes = "根据ID删除绿色通道")
	@RequestMapping(value = "/deleteGreenChanneById", method = RequestMethod.GET)
	public int deleteGreenChanneById( @RequestParam Long id  ){
		return greenChanneService.deleteGreenChanneById(  id  );
	}
	@ApiOperation(value = "根据ID获取绿色通道", notes = "根据ID获取绿色通道")
	@RequestMapping(value = "/getGreenChanneById", method = RequestMethod.GET)
	public GreenChanne getGreenChanneById( @RequestParam Long id  ){
		return greenChanneService.getGreenChanneById(  id  );
	}
 
	@ApiOperation(value = "根据对象获取绿色通道", notes = "根据对象获取绿色通道")
	@RequestMapping(value = "/getGreenChannes", method = RequestMethod.POST)
	public List<GreenChanne> getGreenChannes( @RequestBody GreenChanne greenChanne){
		return greenChanneService.getGreenChannes(greenChanne);

 	}

	@ApiOperation(value = "根据对象分页获取绿色通道", notes = "根据对象分页获取绿色通道")
	@RequestMapping(value = "/getGreenChannesForPage", method = RequestMethod.POST)
	public PageDto<GreenChanne> getGreenChannesForPage(@RequestBody GreenChanne greenChanne,
			@RequestParam(value="page", defaultValue="1")  int page,
			@RequestParam(value="rows", defaultValue="10") int rows,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String order){
		Map<String,String> cols = new HashMap<String,String>();
		cols.put("id", "id");
		cols.put("name", "name");
		cols.put("sex", "sex");
		cols.put("phone", "phone");
		cols.put("address", "address");
		cols.put("describe", "describe");
		cols.put("picture", "picture");
		cols.put("regionId", "region_id");
		Pageable pageable =null;
		if(!StringUtils.isEmpty(order)&&!StringUtils.isEmpty(sort)){
			Sort sortObj =new Sort(Sort.Direction.fromStringOrNull(order), cols.get(sort));
			pageable = new PageRequest(page-1, rows, sortObj);
		}else{
			pageable=new PageRequest(page-1, rows);
		}
		Page<GreenChanne> greenChannes= greenChanneService.getGreenChannesForPage(greenChanne,pageable);
	
		PageDto<GreenChanne> pageDto = new PageDto<GreenChanne>();
		if (greenChannes != null) {
			pageDto.setRows( greenChannes.getContent());
			pageDto.setTotal(greenChannes.getTotalElements());
		} else {
			pageDto.setRows(new ArrayList<GreenChanne>());
			pageDto.setTotal(0l);
		}
		 
		return pageDto;
 	}
}
