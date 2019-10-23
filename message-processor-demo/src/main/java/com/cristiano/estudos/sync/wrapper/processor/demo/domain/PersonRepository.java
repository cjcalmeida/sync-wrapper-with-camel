package com.cristiano.estudos.sync.wrapper.processor.demo.domain;

import java.util.UUID;

public interface PersonRepository {

    void sendResponse(Consumer consumer, String responseID);
}
