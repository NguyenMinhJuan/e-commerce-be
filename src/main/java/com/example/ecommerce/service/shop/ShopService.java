package com.example.ecommerce.service.shop;

import com.example.ecommerce.enums.AccountStatus;
import com.example.ecommerce.enums.RoleName;
import com.example.ecommerce.enums.ShopStatus;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.Shop;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.IShopRepo;
import com.example.ecommerce.repository.IUserRepo;
import com.example.ecommerce.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ShopService implements IShopService {

    @Autowired
    private IShopRepo shopRepository;

    @Autowired
    private IUserRepo userRepository;

    @Autowired
    private IRoleService roleService;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder; //lỗi thi xoa doan nay di

    @Override
    public Iterable<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public void registerShop(Shop shop,User user) {
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role defaultRole = roleService.findByName(RoleName.ROLE_MERCHANT);
        roles.add(defaultRole);
        user.setRoles(roles);
        userRepository.save(user);

        shop.setUser(user);

        shop.setShopStatus(ShopStatus.PENDING);
        shopRepository.save(shop);
    }

    @Override
    public void setShopStatus(Shop shop, ShopStatus status) {
        shop.setShopStatus(status);
        shopRepository.save(shop);
    }

    @Override
    public Shop findByUser(User user) {
       return shopRepository.findByUser(user);
    }
}
