package com.example.ecommerce.service.role;

import com.example.ecommerce.enums.RoleName;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.service.IGenericService;

public interface IRoleService extends IGenericService {
    Role findByName(RoleName name);
}
