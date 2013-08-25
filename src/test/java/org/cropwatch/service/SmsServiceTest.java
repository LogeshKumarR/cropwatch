package org.cropwatch.service;

import org.junit.Test;

public class SmsServiceTest {
    @Test
    public void shouldSendSMSToPhoneHash() {
        new SmsService().sendPushMessage("Test SMS","4ac2eb6f-aced-49da-8286-c99da0a77b06");
    }

}
