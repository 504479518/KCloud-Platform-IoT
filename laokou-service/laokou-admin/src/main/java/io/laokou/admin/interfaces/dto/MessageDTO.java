package io.laokou.admin.interfaces.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
/**
 * @author Kou Shenhai
 */
@Data
public class MessageDTO {

    /**
     * 接收者
     */
    private Set<String> receiver;

    @NotBlank(message = "请输入标题")
    private String title;

    @NotBlank(message = "请输入内容")
    private String content;

    @NotNull(message = "发送渠道不为空")
    private Integer sendChannel;

    private String username;

    private Long userId;

    /**
     * 0通知 1提醒
     */
    private Integer type;
}
