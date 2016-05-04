package com.sl.dao.mapper;

import com.sl.dao.entity.GWhsEntity;
import java.math.BigDecimal;

public interface GWhsEntityMapper {
/*<AUTOGEN--BEGIN>*/

    int deleteByPrimaryKey(BigDecimal recid);

    int insert(GWhsEntity record);

    int insertSelective(GWhsEntity record);

    GWhsEntity selectByPrimaryKey(BigDecimal recid);

    int updateByPrimaryKeySelective(GWhsEntity record);

    int updateByPrimaryKey(GWhsEntity record);

/*<AUTOGEN--END>*/
}
