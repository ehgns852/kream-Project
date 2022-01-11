package com.nklcb.kream.repository;

import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.repository.querydsl.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long>, ItemRepositoryCustom {

}
