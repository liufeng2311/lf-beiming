package com.beiming.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.beiming.BaseMapper;
import com.beiming.dto.response.PermissionDTO;
import com.beiming.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{
  List<PermissionDTO> getUserPermission(String userId);
}
