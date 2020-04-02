package org.justd.commonmistakes.concurrenttool.threadlocal;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhangjd
 * @title: ThreadLocalDemo
 * @description:
 * @date 2020/3/290:12
 */
@RestController
@RequestMapping("threadlocal")
public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("wrong")
    public JSONObject wrong(@RequestParam("userId") Integer userId) {
        String before = Thread.currentThread().getName() + " : " + currentUser.get();
        currentUser.set(userId);
        String after = Thread.currentThread().getName() + " : " + currentUser.get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("before", before);
        jsonObject.put("after", after);
        return jsonObject;
    }

    @GetMapping("right")
    public JSONObject right(@RequestParam("userId") Integer userId) {
        String before = Thread.currentThread().getName() + " : " + currentUser.get();
        currentUser.set(userId);
        try {

            String after = Thread.currentThread().getName() + " : " + currentUser.get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("before", before);
            jsonObject.put("after", after);
            return jsonObject;
        } finally {
            currentUser.remove();
        }
    }
}

