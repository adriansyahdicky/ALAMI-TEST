package com.id.cooperative.util;

import org.springframework.data.domain.Page;

public final class PageableUtil {

    public static <T> Pagination pageToPagination(Page<T> page) {
        return Pagination.builder()
                .pageSize(page.getSize())
                .currentPage(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalRecords(page.getTotalElements())
                .isFirstPage(page.isFirst())
                .isLastPage(page.isLast())
                .build();
    }

}
