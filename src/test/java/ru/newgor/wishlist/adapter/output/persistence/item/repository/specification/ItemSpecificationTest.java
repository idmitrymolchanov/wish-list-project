package ru.newgor.wishlist.adapter.output.persistence.item.repository.specification;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemSpecificationTest {

    @Test
    void shouldSortByPriority() {
        Pageable pageable = ItemSpecification.getPageable(10, 20, "PRIORITY");

        assertThat(pageable.getPageNumber()).isEqualTo(2);
        assertThat(pageable.getPageSize()).isEqualTo(10);

        Sort.Order order = pageable.getSort().iterator().next();
        assertThat(order.getProperty()).isEqualTo("priority");
        assertThat(order.getDirection()).isEqualTo(Sort.Direction.DESC);
    }

    @Test
    void shouldUseDefaultSortField() {
        Pageable pageable = ItemSpecification.getPageable(10, 0, null);

        Sort.Order order = pageable.getSort().iterator().next();
        assertThat(order.getProperty()).isEqualTo("createDate");
    }
}
