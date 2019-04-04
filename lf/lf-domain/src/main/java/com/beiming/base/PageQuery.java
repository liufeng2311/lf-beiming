package com.beiming.base;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageQuery implements Serializable {

  private static final long serialVersionUID = -8137215085424148538L;
  
  @NotNull(message = "当前页码不能为空。")
  @Min(value = 1, message = "当前页码必须大于0。")
  private Integer pageIndex;

  @NotNull(message = "每页的数量不能为空。")
  @Min(value = 1, message = "每页的数量必须大于0。")
  private Integer pageSize;

  public PageQuery(Integer pageIndex, Integer pageSize) {
    if (pageIndex != null && pageSize != null) {
      this.pageIndex = pageIndex;
      this.pageSize = pageSize;
    }
  }

  public PageQuery() {

  }
}
