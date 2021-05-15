package com.xbyrh.service.impl;

import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.entity.UserRoleRef;
import com.xbyrh.repo.entity.UserRoleRefExample;
import com.xbyrh.repo.mapper.UserRoleRefMapper;
import com.xbyrh.service.IRoleService;
import com.xbyrh.service.IUserRoleRefService;
import com.xbyrh.service.context.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
@Service
public class UserRoleRefServiceImpl implements IUserRoleRefService {

    @Autowired
    private UserRoleRefMapper userRoleRefMapper;

    @Autowired
    private IRoleService roleService;

    @Override
    @Transactional
    public void addUserRoleRef(Long uid, List<String> codeList) {
        User user = AuthContext.getUser();
        deleteByUid(uid);

        for (String code : codeList) {
            UserRoleRef userRoleRef = new UserRoleRef();
            userRoleRef.setUid(uid);

            Role role = roleService.findByCode(code);

            userRoleRef.setRoleId(role.getId());

            userRoleRef.setModifier(user.getUid());
            userRoleRef.setCreator(user.getUid());

            userRoleRefMapper.insertSelective(userRoleRef);
        }
    }

    @Override
    public List<UserRoleRef> findByUid(Long uid) {
        UserRoleRefExample example = new UserRoleRefExample();
        example.createCriteria().andUidEqualTo(uid).andIsDeleteEqualTo(0);

        return userRoleRefMapper.selectByExample(example);
    }

    private void deleteByUid(Long uid) {
        UserRoleRefExample example = new UserRoleRefExample();
        example.createCriteria().andUidEqualTo(uid).andIsDeleteEqualTo(0);
        List<UserRoleRef> userRoleRefList = userRoleRefMapper.selectByExample(example);
        userRoleRefList.forEach(x -> x.setIsDelete(1));
        userRoleRefList.forEach(x -> userRoleRefMapper.updateByPrimaryKeySelective(x));
    }
}
