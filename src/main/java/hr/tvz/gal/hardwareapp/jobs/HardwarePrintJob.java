package hr.tvz.gal.hardwareapp.jobs;

import hr.tvz.gal.hardwareapp.hardware.Hardware;
import hr.tvz.gal.hardwareapp.hardware.HardwareRepository;
import hr.tvz.gal.hardwareapp.hardware.HardwareServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class HardwarePrintJob extends QuartzJobBean {

    private Logger log = LoggerFactory.getLogger(HardwarePrintJob.class);
    private HardwareRepository repository;

    public HardwarePrintJob(HardwareRepository rep){
        this.repository = rep;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        final List<Hardware> hardwareList = repository.findAll();

        if(hardwareList.size() > 0){
            log.info("Available hardware:");
            log.info("--------------------------");
            hardwareList.stream()
                    .filter(h -> h.getStock()>0)
                    .forEach(h -> log.info(h.getName()+" "+h.getStock()));
            log.info("--------------------------");
        }else{
            log.info("No available hardware.");
        }
    }
}
