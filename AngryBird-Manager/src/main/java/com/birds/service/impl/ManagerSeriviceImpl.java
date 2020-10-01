package com.birds.service.impl;

import com.birds.configs.JwtConfig;
import com.birds.mapper.ManagerMapper;
import com.birds.pojo.CodeMsg;
import com.birds.pojo.Manager;
import com.birds.pojo.ManagerVo;
import com.birds.pojo.Result;
import com.birds.service.ManagerService;
import com.birds.utils.JsonUtils;
import com.birds.utils.MD5Utils;
import com.birds.utils.PageRequest;
import com.birds.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ManagerSeriviceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Result<ManagerVo> login(String username, String password) {
        //根据用户名与密码获取用户
        //密码加密
        password = MD5Utils.md5(password);
        Manager manager = managerMapper.findManagerByUsernameAndPwd(username, password);
        //判断用户是否存在
        if(StringUtils.isEmpty(manager)){
            return Result.error(CodeMsg.USERNOTEXISTS);
        }
        //设置tonken
        try {
            ManagerVo vo = ManagerVo.getManagerVo(manager);
            String token = jwtConfig.createToken(JsonUtils.objectToJson(vo));
            vo.setToken(token);
            return Result.success(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(CodeMsg.USERNOTEXISTS);
    }

    @Override
    public Result findManagers(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Manager> managers = managerMapper.findManagers(pageRequest.getQuery());
        PageInfo<Manager> pageInfo = new PageInfo<>(managers);
        return Result.success(pageInfo);
    }

    @Override
    public Result addManager(Manager manager) {
        try {
            //加密密码
            manager.setPassword(MD5Utils.md5(manager.getPassword()));
            managerMapper.addManager(manager);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(CodeMsg.DBERROR);
        }
        ManagerVo managerVo = ManagerVo.getManagerVo(manager);
        return Result.success(managerVo);
    }

    @Override
    public Result updateManager(Manager manager) {

        Integer row = managerMapper.updateManager(manager);
        if(row<=0){
            return Result.error(CodeMsg.USERNOTFIND);
        }

        Manager manager1 = managerMapper.findManagerById(manager.getId());
        ManagerVo vo = ManagerVo.getManagerVo(manager1);

        return Result.success(vo);
    }

    @Override
    public Result findManagerById(Integer id) {
        Manager manager = managerMapper.findManagerById(id);
        ManagerVo vo = ManagerVo.getManagerVo(manager);
        return Result.success(vo);
    }

    @Override
    public Result deleteManagerById(Integer id) {
        Integer row = managerMapper.deleteManagerById(id);
        if(row<=0){
            return Result.error(CodeMsg.USERDELERROR);
        }
        return Result.success(null);
    }
}
