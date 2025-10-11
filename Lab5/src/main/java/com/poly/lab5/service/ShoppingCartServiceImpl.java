package com.poly.lab5.service;

import com.poly.lab5.model.DB;
import com.poly.lab5.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Integer, Item> cart = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item item = cart.get(id);
        if (item == null) {
            Item dbItem = DB.items.get(id);
            if (dbItem != null) {
                item = new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 1);
                cart.put(id, item);
            }
        } else {
            item.setQty(item.getQty() + 1);
        }
        return item;
    }

    @Override
    public void remove(Integer id) {
        cart.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = cart.get(id);
        if (item != null) {
            item.setQty(qty);
        }
        return item;
    }

    @Override
    public void clear() {
        cart.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return cart.values();
    }

    @Override
    public int getCount() {
        return cart.values().stream()
                .mapToInt(Item::getQty)
                .sum();
    }

    @Override
    public double getAmount() {
        return cart.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
    }
}
