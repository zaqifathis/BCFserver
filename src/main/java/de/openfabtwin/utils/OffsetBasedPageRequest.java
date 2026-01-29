package de.openfabtwin.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.AbstractPageRequest;

public class OffsetBasedPageRequest extends AbstractPageRequest {

    private final long offset;
    private final Sort sort;

    public OffsetBasedPageRequest(long offset, int limit, Sort sort) {
        super(limit == 0 ? 0 : (int) (offset / limit), limit);
        this.offset = offset;
        this.sort = sort;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(offset + getPageSize(), getPageSize(), sort);
    }

    @Override
    public Pageable previous() {
        long newOffset = Math.max(offset - getPageSize(), 0);
        return new OffsetBasedPageRequest(newOffset, getPageSize(), sort);
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, getPageSize(), sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetBasedPageRequest(
                (long) pageNumber * getPageSize(),
                getPageSize(),
                sort
        );
    }
}

