package com.jxau.mapper;

import com.jxau.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface AdminMapper {
    Admin findAdminByNameAndPassword(@Param("name") String name, @Param("password") String password);


}
