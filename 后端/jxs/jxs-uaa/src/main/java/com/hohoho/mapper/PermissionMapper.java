package com.hohoho.mapper;

import com.hohoho.entity.JPerm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tj_perm \n" +
            "WHERE\n" +
            "\tperm_id IN (\n" +
            "\tSELECT\n" +
            "\t\tperm_id \n" +
            "\tFROM\n" +
            "\t\tj_role_perm \n" +
            "WHERE\n" +
            "\trole_id IN ( SELECT role_id FROM j_role_user WHERE user_id = #{user_id} ))")
    List<JPerm> selectPerm(@Param("user_id") Integer userId);
}
