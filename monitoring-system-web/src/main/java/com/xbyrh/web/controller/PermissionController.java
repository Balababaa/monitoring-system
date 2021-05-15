package com.xbyrh.web.controller;

import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.model.bo.PermissionSaveBO;
import com.xbyrh.repo.model.bo.RoleSaveBO;
import com.xbyrh.repo.model.mapper.PermissionConverter;
import com.xbyrh.repo.model.mapper.RoleConverter;
import com.xbyrh.repo.model.params.PermissionListParam;
import com.xbyrh.repo.model.params.PermissionSaveParam;
import com.xbyrh.repo.model.params.RoleListParam;
import com.xbyrh.repo.model.params.RoleSaveParam;
import com.xbyrh.repo.model.support.BaseResponse;
import com.xbyrh.repo.model.support.PaginationResponse;
import com.xbyrh.repo.model.vo.PermissionListVO;
import com.xbyrh.repo.model.vo.RoleListVO;
import com.xbyrh.service.IPermissionService;
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
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private PermissionConverter permissionConverter;

    @GetMapping("list")
    public PaginationResponse<PermissionListVO> list(PermissionListParam permissionListParam) {
        List<Permission> permissionList = permissionService.listByCode(permissionListParam.getCode(), permissionListParam.getPage(),
                                                                 permissionListParam.getLimit());

        Long total = permissionService.countByCode(permissionListParam.getCode());


        return PaginationResponse.ok(total, permissionConverter.toVoList(permissionList));
    }

    @PostMapping("save")
    public BaseResponse<String> save(@RequestBody PermissionSaveParam permissionSaveParam){
        PermissionSaveBO permissionSaveBO = permissionConverter.toBO(permissionSaveParam);
        permissionService.save(permissionSaveBO);
        return BaseResponse.ok();
    }

    @GetMapping("list_all")
    public List<PermissionListVO> list() {
        List<Permission> permissionList = permissionService.listAll();
        return permissionConverter.toVoList(permissionList);
    }

}
