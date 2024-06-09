package com.digi.bxmd.base.repository

import org.springframework.data.domain.Sort
import javax.validation.Valid


open class BaseSearchCriteria<T> {
    @Valid
    var searchCriteria: T? = null

    var page: Int? = 0
        set(value) {
            field = if (value != null && value <= 0) 0 else value
        }

    var pageSize: Int? = DEFAULT_ITEM_PER_PAGE
        set(value) {
            field = if (value != null && value <= 0) 0 else value
        }

    var direction: String? = Sort.Direction.DESC.toString()
        set(value) {
            field = if (value == null || value.trim().isEmpty()) Sort.Direction.DESC.toString() else value
        }

    var sortBy: String? = DEFAULT_SORT_BY
        set(value) {
            field = if (value == null || value.trim().isEmpty()) DEFAULT_SORT_BY else value
        }

    companion object {
        const val DEFAULT_ITEM_PER_PAGE = 20
        const val DEFAULT_SORT_BY = "createdDate"
    }

    override fun toString(): String {
        return "BaseSearchCriteria(searchCriteria=$searchCriteria, page=$page, pageSize=$pageSize, direction=$direction, sortBy=$sortBy)"
    }
}
