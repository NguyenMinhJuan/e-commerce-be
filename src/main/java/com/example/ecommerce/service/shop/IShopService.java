package com.example.ecommerce.service.shop;

import com.example.ecommerce.enums.ShopStatus;
import com.example.ecommerce.model.Shop;
import com.example.ecommerce.service.IGenericService;

public interface IShopService extends IGenericService<Shop, Number> {
    void registerShop(Shop shop);
    void setShopStatus(Shop shop, ShopStatus status);
}
