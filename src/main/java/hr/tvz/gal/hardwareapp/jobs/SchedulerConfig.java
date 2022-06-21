package hr.tvz.gal.hardwareapp.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    private static final String HARDWARE_PRINT_JOB_IDENTITY = "hardwarePrintJob";
    private static final String HARDWARE_PRINT_TRIGGER = "hardwarePrintTrigger";
    private static final String HARDWARE_PRINT_TRIGGER_WEEKEND = "hardwarePrintNonWorking";

    @Bean
    public JobDetail hardwarePrintJobDetail() {
        return JobBuilder.newJob(HardwarePrintJob.class).withIdentity(HARDWARE_PRINT_JOB_IDENTITY)
                .storeDurably().build();
    }


    @Bean
    public SimpleTrigger hardwarePrintTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();

        return TriggerBuilder.newTrigger().forJob(hardwarePrintJobDetail())
                .withIdentity(HARDWARE_PRINT_TRIGGER).withSchedule(scheduleBuilder).build();
    }


    @Bean
    public CronTrigger hardwarePrintNonWorking(){
        return TriggerBuilder.newTrigger().forJob(hardwarePrintJobDetail())
                .withIdentity(HARDWARE_PRINT_TRIGGER_WEEKEND)
                .withSchedule(CronScheduleBuilder.atHourAndMinuteOnGivenDaysOfWeek(0,0,1,2)).build();

    }
}
