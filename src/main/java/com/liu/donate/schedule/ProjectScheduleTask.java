package com.liu.donate.schedule;

/*
 * @author  LiuHuiPeng
 * @date    2022/4/12 13:37
 */

import com.liu.donate.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class ProjectScheduleTask {

    @Autowired
    private DonationProjectService donationProjectService;

    @Async
    @Scheduled(fixedDelay = 5000)
    public void updateProjectStatus(){
        donationProjectService.updateStatus();
    }
}
