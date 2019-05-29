package cn.wolfcode.rbac.query;

import com.alibaba.druid.filter.AutoLoad;
import com.mysql.jdbc.StringUtils;
import lombok.*;

/**
 * Created by Albert on 2019/5/27.
 */
@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId = -1L;

    public String getKeyword(){
        if(StringUtils.isNullOrEmpty(keyword)){
            return null;
        }else {
            return keyword;
        }
    }
}
