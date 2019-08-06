package com.bee.springboot.controller;


import com.bee.springboot.annotation.HandlingTime;
import com.bee.springboot.entity.TestValidateBean;
import com.bee.springboot.entity.User;
import com.bee.springboot.entity.constrains.MiniValidation;
import com.bee.springboot.service.UserService;
import com.bee.springboot.util.CommonUtil;
import com.bee.springboot.util.PageUtil;
import com.bee.springboot.util.RedisUtil;
import com.bee.springboot.util.validation.ResponseStatusEnum;
import com.bee.springboot.util.validation.ValidateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

//import com.bee.springboot.util.ExcelUtil;
//import com.bee.springboot.util.exception.ExcelException;
//import com.bee.springboot.util.exception.ExcelException;

@RestController  //注解相当于@ResponseBody ＋ @Controller合在一起的作用
@RequestMapping("/user")
@Api(tags = "测试swager")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private RedisUtil redisUtil;

	private LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>(){
		{
			put("id", "id");
			put("enterpriseName", "归属企业");
			put("port", "端口号码");
			put("portName", "端口识别名称");
			put("portPurpose", "端口用途");
			put("areaName", "所属区域");
			put("portLength", "端口固定位数");
			put("maxLength", "拓展最大位数");
			put("minLength", "拓展最小位数");
			put("mark", "备注");
		}
	};

	/*@RequestMapping(value = "/excelTemplate", method = RequestMethod.GET)
	public Map<String,Object> exportExcel(HttpServletResponse response) {
		Map<String,Object> retMap = new HashMap<>();
		try {
			LinkedHashMap<String, String> map = new LinkedHashMap<>();
			map.putAll(fieldMap);
			map.remove("id");
			ExcelUtil.listToExcel(Collections.emptyList(), map, "port", 50000, response);
			return CommonUtil.setReturnMap("0","下载端口excel模板成功",retMap);
		} catch (ExcelException e) {
			logger.error("下载端口excel模板失败", e);
			return CommonUtil.setReturnMap("0","下载端口excel模板失败",retMap);//Result.error("："+e.getMessage());
		}
		//return null;
	}*/


/*	@ResponseBody
	@RequestMapping(value = "/ports/import", method = RequestMethod.POST)
	public Result importExcel(MultipartHttpServletRequest request) throws IOException {
		try {
			MultipartFile file = request.getFile("importFile");
			if (file == null || file.isEmpty()) {
				return Result.error("请选择导入文件");
			}

			HttpSession session = request.getSession();
			List<MgPlatformUser> mgPlatformUser = (List<MgPlatformUser>) session.getAttribute(UserInfoController.userInfo);
			String userName = mgPlatformUser.get(0).getManager();
			String channelId = mgPlatformUser.get(0).getChannelId();

			List<SMSPort> importPorts = ExcelUtil.excelToList(file.getInputStream(), SMSPort.class, fieldMap, new String[]{});
			for (SMSPort port : importPorts) {
				String enterpriseId = "";
				String enterpriseName = port.getEnterpriseName();
				if (StringUtils.isBlank(enterpriseName)) {
					return Result.error("企业名称不能为空");
				}
				if (StringUtils.isBlank(port.getPort())) {
					return Result.error("端口不能为空");
				}
				if (StringUtils.isBlank(port.getPortName())) {
					return Result.error("识别名称不能为空");
				}
				if (StringUtils.isBlank(port.getPortPurpose())) {
					return Result.error("端口用途不能为空");
				}
				if (StringUtils.isBlank(port.getAreaName())) {
					return Result.error("区域不能为空");
				}
				port.setUpdateStaff(userName);
				port.setChannelId(channelId);

				List<SDKEnterprise> enterpriseList = enterpriseService.findAllEnterprise(enterpriseName, 0, 100, null, null, null);
				for (SDKEnterprise enterprise : enterpriseList) {
					if (enterprise.getEntName().equals(enterpriseName)) {
						enterpriseId = enterprise.getId();
						break;
					}
				}
				if (StringUtils.isBlank(enterpriseId)) {
					return Result.error("导入失败， 企业不存在："+enterpriseName);
				}
				port.setEnterpriseId(enterpriseId);

				String areaName = port.getAreaName();
				for (AreaData area : AreaData.AREA) {
					if (areaName.equals(area.getName())) {
						port.setAreaCode(area.getAreaCode());
						break;
					}
				}
				if (StringUtils.isBlank(port.getAreaCode())) {
					return Result.error("未找到对应地区："+areaName);
				}
			}

			for (SMSPort port : importPorts) {
				boolean exist = portService.portExist(port);
				if (exist) {
					return Result.error("该端口已存在；端口号码："+port.getPort()+"; 地区："+port.getAreaName());
				}
			}
			for (SMSPort port : importPorts) {
				Result result = portService.createOrUpdate(port);
				if (result.hasError()) {
					result.setMessage("导入端口号码（"+port.getPort()+"）时遇到错误："+result.getMessage());
				}
			}
		} catch (ExcelException e) {
			log.error("导入数据失败:", e);
			return Result.error("导入数据失败，请重试："+e.getMessage());
		}
		Result result = new Result();
		result.setCode(Result.OK_CODE);
		result.setMessage("导入成功！");
		return  result;
	}*/


	/**
	 * 批量导出数据
	 */

	/*@RequestMapping(value = "/export", method = RequestMethod.GET)
	public Result exportExcel(@RequestParam(defaultValue = "") String enterpriseId, String port, String sceneName, HttpServletResponse response, HttpServletRequest request) {
		*//*HttpSession session = request.getSession();
		List<MgPlatformUser> mgPlatformUser = (List<MgPlatformUser>) session.getAttribute(UserInfoController.userInfo);
		String channelId = mgPlatformUser.get(0).getChannelId();
		int userid = mgPlatformUser.get(0).getUserId();*//*
		*//*Result<Page<SMSPort>> listResult = portService.fetchPorts(enterpriseId, port, -1, -1,channelId,userid);
		if (listResult.hasError()) {
			return Result.error("导出失败："+listResult.getMessage());
		}*//*
		try {
			ExcelUtil.listToExcel(listResult.getBody(), fieldMap, "port", 50000, response);
		} catch (ExcelException e) {
			log.error("导出端口信息失败", e);
			return Result.error("导出失败："+e.getMessage());
		}
		return null;
	}*/

	/**
	 * 这里有错误，需要接着修改
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	public PageInfo getValidation() {

		PageUtil.startPage(0);
		List<User> users = userService.getUserInfo();
		System.out.println(users.toString());
		return new PageInfo((Page)users);
	}

	/**
	 * 这里有错误，需要接着修改
	 * @return
	 */
	@RequestMapping("/getValidation")
    public Map<String,Object> getUserInfo() {
		logger.info("Start...");
		TestValidateBean tvb = new TestValidateBean();
		tvb.setPhone("15903991821");
		tvb.setDealtime("20190611121212");
		tvb.setProvince("021");
		tvb.setContent("xxxxxx");
		tvb.setServicecode("10010");
		String errorMsg = ValidateUtil.validateGroup(tvb, MiniValidation.class);//后面的MiniValidation.class只是为了分组校验
		Map<String,Object> retMap = new HashMap<>();
		if(StringUtils.isNotEmpty(errorMsg)){
			//return  HNRespStatus.PARAMERR;
			System.out.println("出错了:: "+errorMsg);
			return CommonUtil.setReturnMap("0",ResponseStatusEnum.INVALID_PARAM.getMessage(),retMap);
		}


		List<User> users = userService.getUserInfo();
		retMap.put("beans",users);
		return CommonUtil.setReturnMap("0","请求成功",retMap);
    }


	/**
	 * findAll
	 * Hibernate
	 * @return
	 */
	/*@RequestMapping("/getUserInfo")
	public PageInfo getUserInfo() {
		PageUtil.startPage(0);
		List<User> users = userService.getUserInfo();
		System.out.println(users.toString());
		return new PageInfo((Page)users);
	}*/

	/**
	 * 测试请求
	 * @return
	 */
	@RequestMapping("/testResult")
	public Map<String,Object> testResult() {
		logger.info("Start...");

		Map<String,Object> retMap = new HashMap<>();
		List<User> users = userService.getUserInfo();
		retMap.put("beans",users);
		return CommonUtil.setReturnMap("0","请求成功",retMap);
	}

	@HandlingTime
	@RequestMapping("/addUserInfo")
    public String addUserInfo() {
		User user = new User();

		user.setName("cwh");
		user.setCreateDate(new Date());
		userService.insert(user);
        return "success:"+user.toString();
    }

	/**
	 * http://localhost:8882/user/findOneUserInfo?id=3
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOneUserInfo")
	public String addUserInfo(@RequestParam(value = "id", required = false) Integer id) {

		boolean hakey = redisUtil.hasKey("str");
		System.out.println("haskey:"+hakey);
		String str = (String) redisUtil.get("str");
		System.out.println("str:"+str);//添加了Redis缓存测试

		User user = userService.selectUserById(id);
		return "success:"+user.toString();
	}


	/**
	 * http://localhost:8882/user/deleteUserLists
	 * 批量删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUserLists")
	public String deleteUserLists(@RequestParam(value = "id", required = false) Integer id) {

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(6);
		ids.add(8);
		ids.add(9);
		int count = userService.dynaDeleteList(ids);
		return "success: 删除"+count;
	}
	
}  
