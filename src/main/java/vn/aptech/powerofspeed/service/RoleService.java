package vn.aptech.powerofspeed.service;

import java.util.List;

import vn.aptech.powerofspeed.dto.model.user.RoleDto;

public interface RoleService {

    List<RoleDto> findAll();
}
