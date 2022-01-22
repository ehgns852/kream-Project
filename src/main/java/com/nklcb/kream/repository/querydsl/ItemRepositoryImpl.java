package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.dto.querydsl.QItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.entity.item.QItem;
import com.nklcb.kream.entity.item.QUploadFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.nklcb.kream.entity.item.QItem.*;
import static com.nklcb.kream.entity.item.QUploadFile.*;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    /**
     * 등록한 상품 전체 리스트
     */
    @Override
    public Page<ItemQueryDto> findAllList(Pageable pageable) {
        List<ItemQueryDto> items = queryFactory
                .select(new QItemQueryDto(
                        item.id,
                        item.brandName,
                        item.itemName,
                        item.price,
                        item.stockQuantity,
                        item.timeEntity.createDate))
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

    /**
     * id로 ItemQueryDto 변환해서 조회
     */
    public ItemQueryDto findByIdDto(Long id){
        return queryFactory
                .select(new QItemQueryDto(
                        item.id,
                        item.brandName,
                        item.itemName,
                        item.price,
                        item.stockQuantity,
                        item.timeEntity.createDate,
                        uploadFile.uploadFileName,
                        uploadFile.storeFileName,
                        uploadFile.filePath))
                .from(item)
                .leftJoin(item.attachFile,uploadFile)
                .where(item.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<ItemQueryDto> findBestItem(Pageable pageable) {
        List<ItemQueryDto> content = queryFactory
                .select(new QItemQueryDto(
                        item.id,
                        item.brandName,
                        item.itemName,
                        item.price,
                        item.stockQuantity,
                        item.timeEntity.createDate,
                        uploadFile.uploadFileName,
                        uploadFile.storeFileName,
                        uploadFile.filePath))
                .from(item)
                .leftJoin(item.attachFile, uploadFile)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.id.desc())
                .fetch();

        long countQuery = queryFactory
                .selectFrom(item)
                .leftJoin(item.attachFile, uploadFile)
                .fetchCount();

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery);
    }


}
