package com.yigit.blog_api.post.dto;

import java.util.List;

public record PagedResponse<T>(

        List<T> content,

        int page,

        int size,

        long totalElements,

        int totalPages,

        boolean first,

        boolean last

) {
}