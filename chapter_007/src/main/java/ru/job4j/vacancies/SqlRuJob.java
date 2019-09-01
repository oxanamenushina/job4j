package ru.job4j.vacancies;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class SqlRuJob.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SqlRuJob implements Job {

    private static final Logger LOG = LogManager.getLogger(SqlRuJob.class.getName());

    private Config config;

    public SqlRuJob(String properties) {
        this.config = new Config(properties);
        config.init();
    }

    /**
     * Метод задает действия, выполняемые для планировщиком.
     * @param jobExecutionContext
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOG.info("Getting the time and date from which to start parsing.");
        try (VacanciesStore store = new VacanciesStore(this.config)) {
            VacanciesParser vp = new VacanciesParser(this.getInitialTime(store));
            LOG.info("The beginning of parsing.");
            store.insertVacancies(vp.parse());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Метод получает время создания самой поздней вакансии, добавленной в базу данных,
     * если в базе данных нет вакансий, возвращает начало текущего года.
     * @param store хранилище вакансий.
     * @return время создания самой поздней вакансии или
     * начало текущего года, если в базе данных нет вакансий.
     */
    private long getInitialTime(VacanciesStore store) {
        long time = store.getTimeOfLastVacancy();
        if (time == 0) {
            time = Long.parseLong(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()) + "01010000");
        }
        return time;
    }

    /**
     * Метод запускает планировщик.
     */
    public void startScheduler() {
        try {
            JobDetail job = JobBuilder.newJob(SqlRuJob.class)
                    .withIdentity("job1", "group1")
                    .build();
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(this.config.get("cron.time")))
                    .build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            LOG.info("The scheduler is running.");
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}