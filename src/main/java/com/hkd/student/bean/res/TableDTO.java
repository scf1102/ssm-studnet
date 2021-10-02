package com.hkd.student.bean.res;

import com.hkd.student.constans.Constans;
import lombok.Data;

import java.util.List;
@Data
public class TableDTO<T> {

    private List<T> rows;

    private Long totalCount;


    public long getPageCount(){
        long pageCount;
        if (totalCount % Constans.PAGE_SIZE == 0){
            pageCount = totalCount /Constans.PAGE_SIZE;
        }else {
            pageCount = totalCount /Constans.PAGE_SIZE + 1;
        }
        return pageCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
