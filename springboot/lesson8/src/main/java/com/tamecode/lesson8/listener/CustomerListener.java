package com.tamecode.lesson8.listener;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

/**
 * 对Customer进行监听
 *
 * @Author: LiQiongchao
 * @Date: 2020/8/22 16:55
 */
public class CustomerListener {

    @PrePersist
    public void prePersist(Object source) {
        // source 即为customer对象
        System.out.println("@PrePersist : " + source);
    }

    @PostPersist
    public void PostPersist(Object source) {
        System.out.println("@PostPersist : " + source);
    }

}
