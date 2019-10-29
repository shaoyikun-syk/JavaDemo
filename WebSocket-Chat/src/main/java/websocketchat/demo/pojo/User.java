package websocketchat.demo.pojo;

import lombok.*;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/14 19:08
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Builder
public class User {

    private Long id;
    private String username;
}
