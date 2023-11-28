package com.example.orm_backend.serviceImpl;

import com.example.orm_backend.service.TimeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class TimeServiceImpl implements TimeService {

    private Long start = 0L;

    @Override
    public void loginRecord() {
        start = System.currentTimeMillis();
    }

    @Override
    public Long logoutRecord() {
        return (System.currentTimeMillis() - start);
    }
}
