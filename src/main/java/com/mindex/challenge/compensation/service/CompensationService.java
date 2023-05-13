package com.mindex.challenge.compensation.service;

import com.mindex.challenge.compensation.data.Compensation;

import java.util.List;

public interface CompensationService {
    Compensation create(Compensation compensation);
    List<Compensation> read(String id);
    List<Compensation> readAll(); //just for fun
}
