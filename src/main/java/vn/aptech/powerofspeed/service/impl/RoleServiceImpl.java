package vn.aptech.powerofspeed.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.aptech.powerofspeed.dto.mapper.RoleMapper;
import vn.aptech.powerofspeed.dto.model.user.RoleDto;
import vn.aptech.powerofspeed.model.user.Role;
import vn.aptech.powerofspeed.repository.user.RoleRepository;
import vn.aptech.powerofspeed.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(role -> roleDtos.add(RoleMapper.toRoleDto(role)));

        return roleDtos;
    }
}
