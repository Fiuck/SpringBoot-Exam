package top.lemcoo.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.lemcoo.exam.domain.entity.SysRole;
import top.lemcoo.exam.mapper.SysRoleMapper;

import java.util.List;

@SpringBootTest
class ExamApplicationTests {

    @Autowired
    SysRoleMapper roleMapper;

    @Test
    void contextLoads() {
        List<SysRole> sysRoles = roleMapper.selectRolePermissionByUserId(1L);
        System.out.println(sysRoles);
    }

}
