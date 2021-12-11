package co.com.sofka.calendar.controllers;

import co.com.sofka.calendar.model.ProgramDate;
import co.com.sofka.calendar.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Schedule")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping(value = "/{programId}/{date}")
    public Flux<ProgramDate> getCalendar(@PathVariable("programId") String programId, @PathVariable("date")String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(date, dateTimeFormatter);

        return schedulerService.generateCalendar(programId, startDate);
    }
}
