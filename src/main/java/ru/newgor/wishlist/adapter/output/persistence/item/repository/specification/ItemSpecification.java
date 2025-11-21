package ru.newgor.wishlist.adapter.output.persistence.item.repository.specification;

import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import ru.newgor.wishlist.adapter.output.persistence.item.entity.ItemEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ItemSpecification {

    public static Specification<ItemEntity> filterByParams(String userLogin, String statusCode, Instant createDateFrom, Instant createDateTo) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("userLogin"), userLogin));

            if (statusCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), statusCode));
            }
            if (createDateFrom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDateFrom"), createDateFrom));
            }
            if (createDateTo != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createDateTo"), createDateTo));
            }

            if (query != null) {
                query.orderBy(criteriaBuilder.desc(root.get("createDate")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Pageable getPageable(Integer limit, Integer offset) {
        return PageRequest.of(offset / limit, limit);
    }
}
