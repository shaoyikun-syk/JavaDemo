package websocketchat.demo.pojo;

import lombok.*;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/16 16:15
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResult {
    private Integer pageSize;
    private Long totals;
    private Object obj;
}
