package com.xbyrh.web.controller;

import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.RoleBO;
import com.xbyrh.repo.model.bo.RoleSaveBO;
import com.xbyrh.repo.model.bo.UserSaveBO;
import com.xbyrh.repo.model.mapper.RoleConverter;
import com.xbyrh.repo.model.params.*;
import com.xbyrh.repo.model.support.BaseResponse;
import com.xbyrh.repo.model.support.PaginationResponse;
import com.xbyrh.repo.model.vo.RoleListVO;
import com.xbyrh.repo.model.vo.UserListVO;
import com.xbyrh.service.IRolePermissionRefService;
import com.xbyrh.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private IRolePermissionRefService rolePermissionRefService;

    @GetMapping("list")
    public PaginationResponse<RoleListVO> list(RoleListParam roleListParam) {
        List<RoleBO> roleBOList = roleService.listByCode(roleListParam.getCode(), roleListParam.getPage(),
                                                       roleListParam.getLimit());

        Long total = roleService.countByCode(roleListParam.getCode());


        return PaginationResponse.ok(total, roleConverter.toVoList(roleBOList));
    }

    @PostMapping("save")
    public BaseResponse<String> save(@RequestBody RoleSaveParam roleSaveParam){
        RoleSaveBO roleSaveBO = roleConverter.toBO(roleSaveParam);
        roleService.save(roleSaveBO);
        return BaseResponse.ok();
    }

    @GetMapping("list_all")
    public List<RoleListVO> list() {
        List<RoleBO> roleBOList = roleService.listAll();
        return roleConverter.toVoList(roleBOList);
    }

    @PostMapping ("add")
    public BaseResponse<String> addPermission(@RequestBody PermissionAddParam permissionAddParam){
        List<String> codeList = permissionAddParam.getCodeList();
        rolePermissionRefService.addRolePermissionRef(permissionAddParam.getRoleId(),codeList);
        return BaseResponse.ok();
    }
}
