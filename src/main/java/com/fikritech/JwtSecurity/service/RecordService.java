package com.fikritech.JwtSecurity.service;

import com.fikritech.JwtSecurity.dto.RecordRequest;
import com.fikritech.JwtSecurity.entity.Record;
import com.fikritech.JwtSecurity.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;

    public List<Record> getAllByCreatedBy(String createdBy) {
        return repository.findByCreatedBy(createdBy);
    }

    public Record save(RecordRequest request, String userEmail) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Record record = Record.builder()
                .createdBy(userEmail)
                .date(LocalDate.parse(request.date(), dateFormatter))
                .time(request.time())
                .location(request.location())
                .temperature(request.temperature())
                .build();

        repository.save(record);

        return record;
    }

    public Record updateById(RecordRequest request, Long id, String userEmail) {
        Record record = repository.findById(id).orElseThrow(() -> new RuntimeException("Record not found."));

        if (!record.getCreatedBy().equals(userEmail)) {
            throw new RuntimeException("You can't access this record.");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        record.setCreatedBy(userEmail);
        record.setDate(LocalDate.parse(request.date(), dateFormatter));
        record.setTime(request.time());
        record.setLocation(request.location());
        record.setTemperature(request.temperature());

        repository.save(record);

        return record;
    }

    public String deleteById(Long id, String userEmail) {
        Record record = repository.findById(id).orElseThrow(() -> new RuntimeException("Record not found."));
        if (record.getCreatedBy().equals(userEmail)) {
            repository.deleteById(id);
            return "Record deleted successfully.";
        }
        return "Record delete failed.";
    }

}
