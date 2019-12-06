package com.example.demo_for_security.adapter;

import com.example.demo_for_security.dao.SysResDao;
import com.example.demo_for_security.pojo.SysRes;
import com.lhit.starter.security.adapter.LhitSecurityResourceProtectAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源保护适配器
 *
 * 通过该适配器
 * 告诉系统 哪些url指向的资源是收保护的 需要有相关权限的用户才可以访问
 * 并资源对应的是哪个权限码
 *
 */
@Component
public class MyResProtectAdapter implements LhitSecurityResourceProtectAdapter {

    @Autowired
    private SysResDao sysResDao;

    private List<String> protectUrls = null;

    @Override
    public List<String> getProtectUrlPatterns() {
        if (protectUrls == null) {
            SysRes query = new SysRes();
            query.setActiveFlag(true);
            List<SysRes> resList = sysResDao.select(query);
            protectUrls = resList.stream().map(ele -> ele.getUrl()).collect(Collectors.toList());
        }
        return protectUrls;
    }

}
