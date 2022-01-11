package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.QItemDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.entity.item.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.nklcb.kream.entity.item.QItem.*;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ItemDto> findAllList(Pageable pageable) {
        List<ItemDto> items = queryFactory
                .select(new QItemDto(
                        item.brandName,
                        item.itemName,
                        item.price,
                        item.stockQuantity,
                        item.createDate))
                .from(item)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.id.desc())
                .fetch();

        long countQuery = queryFactory
                .selectFrom(item)
                .fetchCount();

        return PageableExecutionUtils.getPage(items, pageable, () -> countQuery);
    }
}
