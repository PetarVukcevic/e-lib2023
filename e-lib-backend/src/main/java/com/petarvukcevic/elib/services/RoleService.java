package com.petarvukcevic.elib.services;

import com.petarvukcevic.elib.dto.query.RoleQuery;
import com.petarvukcevic.elib.mappers.RoleMapper;
import com.petarvukcevic.elib.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleQuery> findAll()
    {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleQuery)
                .toList();
    }
}
