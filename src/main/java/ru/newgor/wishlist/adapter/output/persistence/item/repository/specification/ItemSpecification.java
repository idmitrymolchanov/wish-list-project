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

    private static final String CREATE_DATE_FIELD = "createDate";

    public static Specification<ItemEntity> filterByParams(String userLogin, String statusCode, Instant createDateFrom, Instant createDateTo) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("userLogin"), userLogin));

            if (statusCode != null) {
                predicates.add(criteriaBuilder.equal(root.get("statusCode"), statusCode));
            }
            if (createDateFrom != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(CREATE_DATE_FIELD), createDateFrom));
            }
            if (createDateTo != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(CREATE_DATE_FIELD), createDateTo));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Pageable getPageable(
            Integer limit,
            Integer offset,
            String sortFieldName
    ) {
        if(sortFieldName == null) {
            sortFieldName = CREATE_DATE_FIELD;
        }
        sortFieldName = switch (sortFieldName) {
            case "PRIORITY" -> "priority";
            case "AMOUNT" -> "amount";
            default -> CREATE_DATE_FIELD;
        };
        Sort sort = Sort.by(Sort.Direction.DESC, sortFieldName);
        return PageRequest.of(offset / limit, limit, sort);
    }
}
