package com.jack.bean;

import lombok.*;

import java.util.Set;

/**
 * 发邮件
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    //邮件主题
    private String subject;
    //邮件信息
    private String message;
    //收件人的邮箱
    private Set<String> receivers;
}
