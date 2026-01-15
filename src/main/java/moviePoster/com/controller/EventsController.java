package moviePoster.com.controller;

import lombok.RequiredArgsConstructor;
import moviePoster.com.dto.request.EventsRequestDto;
import moviePoster.com.dto.response.EventsResponseDto;
import moviePoster.com.service.EventsService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {

    private final EventsService eventsService;


    @GetMapping
    public ResponseEntity<List<EventsResponseDto>> getAll() {
        return ResponseEntity.ok(eventsService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventsResponseDto> create(@RequestBody EventsRequestDto dto) {
        return ResponseEntity.ok(eventsService.create(dto));
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        eventsService.delete(name);
        return ResponseEntity.noContent().build();
    }
}

